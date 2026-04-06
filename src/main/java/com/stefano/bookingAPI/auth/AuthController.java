package com.stefano.bookingAPI.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.bookingAPI.model.dto.LoginRequest;
import com.stefano.bookingAPI.model.dto.LoginResponse;
import com.stefano.bookingAPI.model.dto.RegisterUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
          return ResponseEntity.ok(authService.login(request.getEmail(),request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {
        authService.register(request.getEmail(), request.getName(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

}
