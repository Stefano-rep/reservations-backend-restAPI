package com.stefano.bookingAPI.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stefano.bookingAPI.model.dto.LoginResponse;
import com.stefano.bookingAPI.model.entity.User;
import com.stefano.bookingAPI.model.entity.Role;
import com.stefano.bookingAPI.repository.UserRepository;
import com.stefano.bookingAPI.security.JwtService;


@Service    
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String accessToken = jwtService.generateToken(user);

        return new LoginResponse(accessToken);
    }

    public void register(String email,String name, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(email, name, passwordEncoder.encode(password), Role.USER);
        userRepository.save(user);
    }
}
