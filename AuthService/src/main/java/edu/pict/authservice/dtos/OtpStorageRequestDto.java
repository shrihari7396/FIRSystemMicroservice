package edu.pict.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@ToString
public class OtpStorageRequestDto {
    private String email;
}
