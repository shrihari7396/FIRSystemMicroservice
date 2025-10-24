package edu.pict.authservice.controller;

import edu.pict.authservice.dtos.OtpStorageRequestDto;
import edu.pict.authservice.dtos.OtpValidationRequestDto;
import edu.pict.authservice.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/emailVerification")
public class EmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @GetMapping("/sendOtp")
    public ResponseEntity<?> emailVerification(OtpStorageRequestDto otpStorageRequestDto) {
        if(emailVerificationService.createOtp(otpStorageRequestDto)) {
            return ResponseEntity.status(HttpStatus.OK).body("OTP sent Successfully !!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Their Error occurs during Sending OTP !!!");
    }

    @PostMapping("/validateOtp")
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequestDto otpValidationRequestDto) {
        if(emailVerificationService.validateOtp(otpValidationRequestDto)) {
            return ResponseEntity.status(HttpStatus.OK).body("OTP Validated Successfully !!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("OTP is Invalid Successfully !!!");
    }
}
