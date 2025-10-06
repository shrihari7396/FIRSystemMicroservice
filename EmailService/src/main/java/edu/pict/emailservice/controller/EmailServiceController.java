package edu.pict.emailservice.controller;

import edu.pict.emailservice.dtos.OtpStorageRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EmailServiceController {

    @GetMapping("/email/genrateOtp")
    public ResponseEntity<?> createOtp(@RequestBody OtpStorageRequestDto otpStorageRequestDto) {

        return null;
    }
}
