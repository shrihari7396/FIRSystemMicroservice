package edu.pict.emailservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("spring.redis.data.tls")
    private String OTP_TTL_MINUTES;

    public String genrateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999)); // 6-digit OTP

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("OTP_" + email, otp, Duration.ofMinutes(Integer.parseInt(OTP_TTL_MINUTES)));

        return otp;
    }

}
