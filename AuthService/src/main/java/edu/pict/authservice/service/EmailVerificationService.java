package edu.pict.authservice.service;

import edu.pict.authservice.client.EmailServiceFeignClient;
import edu.pict.authservice.dtos.OtpStorageRequestDto;
import edu.pict.authservice.dtos.OtpValidationRequestDto;
import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailServiceFeignClient emailServiceFeignClient;

    @Autowired
    private AppUserRepository appUserRepository;

    public boolean createOtp(OtpStorageRequestDto otpStorageRequestDto) {
        ResponseEntity<?> response = emailServiceFeignClient.createOtp(otpStorageRequestDto);
        return response.getStatusCode() == HttpStatus.OK;
    }

    public boolean validateOtp(OtpValidationRequestDto otpValidationRequestDto) {
        ResponseEntity<?> response = emailServiceFeignClient.validateOtp(otpValidationRequestDto);
        if(response.getStatusCode() == HttpStatus.OK) {
            AppUser appUser = appUserRepository.findByEmail(otpValidationRequestDto.getEmail());
            appUser.setVerified(true);
            appUserRepository.save(appUser);
            return true;
        }
        return false;
    }
}
