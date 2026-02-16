package com.bank.authentication_service.service;

import com.bank.authentication_service.entity.UserCredential;
import com.bank.authentication_service.repository.UserCredentialRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserCredentialRepository repository;

    public AuthService(UserCredentialRepository repository) {
        this.repository = repository;
    }

    public String login(String email, String password) {

        UserCredential user = repository.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // Generate simple session token
        return UUID.randomUUID().toString();
    }
}
