package edu.pict.emailservice.controller;

import edu.pict.emailservice.dtos.OtpStorageRequestDto;
import edu.pict.emailservice.dtos.OtpValidationRequestDto;
import edu.pict.emailservice.service.OtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailServiceController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/email/genrateOtp")
    public ResponseEntity<?> createOtp(@RequestBody OtpStorageRequestDto otpStorageRequestDto) throws MessagingException {
        String otp = otpService.generateOtp(otpStorageRequestDto.getEmail());

        if(otp == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OTP IS NULL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Otp Sent Successfully");
    }

    @PostMapping()
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequestDto otpValidationRequestDto) throws MessagingException {
        if(otpService.verifyOtp(otpValidationRequestDto.getEmail(), otpValidationRequestDto.getOtp())){
            return ResponseEntity.status(HttpStatus.OK).body("Otp Sent Successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP IS NOT VALID");
    }
}
