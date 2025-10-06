package edu.pict.emailservice.service;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
@Slf4j
public class OtpService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailSenderService emailSenderService;

    // Read OTP TTL from application.yml/properties, default 2 minutes
    @Value("${otp.ttl.minutes:2}")
    private int OTP_TTL_MINUTES;

    /**
     * Generate OTP, store in Redis, send email
     */
    public String generateOtp(String email) throws MessagingException {
        String otp = String.format("%06d", new Random().nextInt(999999)); // 6-digit OTP

        // Store in Redis with TTL
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("OTP_" + email, otp, Duration.ofMinutes(OTP_TTL_MINUTES));

        // Send OTP via email
        String subject = "Your OTP Verification Code";
        String body = "Your OTP is: " + otp + "\nIt is valid for " + OTP_TTL_MINUTES + " minutes.";
        emailSenderService.sendEmail(email, subject, body);

        log.info("OTP generated for {}: {}", email, otp);

        return otp;
    }

    /**
     * Verify OTP
     */
    public boolean verifyOtp(String email, String otp) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String storedOtp = ops.get("OTP_" + email);

        if (storedOtp == null) {
            return false; // OTP expired or not found
        }

        if (storedOtp.equals(otp)) {
            redisTemplate.delete("OTP_" + email); // Remove after successful verification
            return true;
        }

        return false;
    }
}
