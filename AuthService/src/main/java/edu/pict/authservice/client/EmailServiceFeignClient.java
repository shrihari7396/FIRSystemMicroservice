package edu.pict.authservice.client;

import edu.pict.authservice.dtos.OtpStorageRequestDto;
import edu.pict.authservice.dtos.OtpValidationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient( name = "EmailService")
public interface EmailServiceFeignClient {
    @PostMapping("email/genrateOtp")
    ResponseEntity<?> createOtp(@RequestBody OtpStorageRequestDto otpStorageRequestDto);

    @PostMapping("/email/validate")
    ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequestDto otpValidationRequestDto);
}
