package edu.pict.authservice.dtos;

import edu.pict.authservice.model.Address;
import edu.pict.authservice.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenResponseDto {
    private String  token;
    private UUID uuid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Role role;
}
