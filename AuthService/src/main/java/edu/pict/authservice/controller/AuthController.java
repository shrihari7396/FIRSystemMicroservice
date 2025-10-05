package edu.pict.authservice.controller;

import edu.pict.authservice.dtos.LoginRequestDto;
import edu.pict.authservice.dtos.TokenResponseDto;
import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.Login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        AppUser saved = null;
        try {
            saved = authService.Register(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return (saved != null) ? ResponseEntity.status(HttpStatus.CREATED).body(saved.getEmail()) : null;
    }
}
