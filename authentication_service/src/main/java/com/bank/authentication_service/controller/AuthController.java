package com.bank.authentication_service.controller;

import com.bank.authentication_service.dto.LoginRequest;
import com.bank.authentication_service.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest request) {
        return service.login(request.getEmail(), request.getPassword());
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hi this is Authentication service created by Thiru.";
    }
}
