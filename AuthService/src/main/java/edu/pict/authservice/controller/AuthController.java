package edu.pict.authservice.controller;

import edu.pict.authservice.client.EmailServiceFeignClient;
import edu.pict.authservice.dtos.LoginRequestDto;
import edu.pict.authservice.dtos.OtpStorageRequestDto;
import edu.pict.authservice.dtos.OtpValidationRequestDto;
import edu.pict.authservice.dtos.TokenResponseDto;
import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailServiceFeignClient  emailServiceFeignClient;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
        log.info("In AuthController.login, {}", loginRequestDto);
        TokenResponseDto tokenResponseDto =authService.Login(loginRequestDto);
        if(!tokenResponseDto.isVerified()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not verified !!!");
        }
        log.info("tokenResponseDto={}", tokenResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        AppUser saved = null;
        try {
            user.setVerified(false);
            saved = authService.Register(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return (saved != null) ? ResponseEntity.status(HttpStatus.CREATED).body(saved.getEmail()) : null;
    }

    @GetMapping("/emailVerification/sendOtp")
    public ResponseEntity<?> emailVerification(OtpStorageRequestDto otpStorageRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(emailServiceFeignClient.createOtp(otpStorageRequestDto));
    }

    @PostMapping("/emailVerificaation/validateOtp")
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequestDto otpValidationRequestDto) {

    }








}
