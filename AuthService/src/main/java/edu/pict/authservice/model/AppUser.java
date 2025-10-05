package edu.pict.authservice.model;

import edu.pict.authservice.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }
}
