package edu.pict.authservice.repository;

import edu.pict.authservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    UserDetails findByUsername(String username);

    AppUser findByEmail(String email);
}
