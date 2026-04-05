package com.stefano.order_management_api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.order_management_api.model.dto.LoginRequest;
import com.stefano.order_management_api.model.dto.LoginResponse;
import com.stefano.order_management_api.model.dto.RefreshTokenRequest;
import com.stefano.order_management_api.model.dto.RegisterUserRequest;

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

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authService.refresh(request.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequest request){
        authService.logout(request.getToken());
        return ResponseEntity.ok().build();
    } 
}
