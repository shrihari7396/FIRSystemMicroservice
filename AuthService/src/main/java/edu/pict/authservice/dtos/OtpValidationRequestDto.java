package edu.pict.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class OtpValidationRequestDto {
    private String email;
    private String otp;
}