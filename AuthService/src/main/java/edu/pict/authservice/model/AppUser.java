package edu.pict.authservice.model;

import edu.pict.authservice.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column( nullable = true)
    private String firstName;
    @Column( nullable = true)
    private String lastName;
    @Column( nullable = false, unique = true)
    private String username;
    @Column( nullable = false)
    private String password;
    @Column( nullable = true)
    private boolean isVerified;
    @Column( nullable = true)
    private String aadharNumber;
    @Column( nullable = true)
    private String email;
    @Column( nullable = true)
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    Role role;

}
