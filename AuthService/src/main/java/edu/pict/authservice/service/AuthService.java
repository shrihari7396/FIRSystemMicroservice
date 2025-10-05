package edu.pict.authservice.service;

import edu.pict.authservice.dtos.LoginRequestDto;
import edu.pict.authservice.dtos.TokenResponseDto;
import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.repository.AppUserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser Register(AppUser username) {
        return appUserRepository.save(username);
    }

    public TokenResponseDto Login(LoginRequestDto loginRequestDto) {
        AppUser appUser = appUserRepository.findByEmail(loginRequestDto.getEmail());
        if(appUser == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }

        String token = jwtService.generateToken(appUser);

        return TokenResponseDto.builder()
                .token(token)
                .uuid(appUser.getUuid())
                .username(appUser.getUsername())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(loginRequestDto.getEmail())
                .address(appUser.getAddress())
                .role(appUser.getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username);
    }
}
