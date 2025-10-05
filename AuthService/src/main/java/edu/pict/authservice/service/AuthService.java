package edu.pict.authservice.service;

import edu.pict.authservice.dtos.LoginRequestDto;
import edu.pict.authservice.dtos.TokenResponseDto;
import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.repository.AppUserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public AppUser Register(AppUser appUser) {
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public TokenResponseDto Login(LoginRequestDto loginRequestDto) throws AuthenticationException {
        AppUser appUser = appUserRepository.findByEmail(loginRequestDto.getEmail());
        if(appUser == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }

        if(!encoder.matches(loginRequestDto.getPassword(), appUser.getPassword())){
            throw new AuthenticationException("Invalid password");
        }

        String token = jwtService.generateToken(appUser);

        return TokenResponseDto.builder()
                .token(token)
                .uuid(appUser.getUuid())
                .username(appUser.getUsername())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .address(appUser.getAddress())
                .role(appUser.getRole())
                .isVerified(appUser.isVerified())
                .build();
    }
}
