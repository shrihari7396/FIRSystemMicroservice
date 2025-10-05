package edu.pict.authservice.service;

import edu.pict.authservice.model.AppUser;
import edu.pict.authservice.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser Register(AppUser username) {
        return appUserRepository.save(username);
    }

    public AppUser Login(AppUser username) {
        return null;
    }
}
