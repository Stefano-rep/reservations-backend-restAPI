package com.stefano.order_management_api.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stefano.order_management_api.model.dto.LoginResponse;
import com.stefano.order_management_api.model.entity.RefreshToken;
import com.stefano.order_management_api.model.entity.User;
import com.stefano.order_management_api.model.enums.Role;
import com.stefano.order_management_api.repository.UserRepository;
import com.stefano.order_management_api.security.JwtService;
import com.stefano.order_management_api.service.RefreshTokenService;

@Service    
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefrehToken(user);

        return new LoginResponse(accessToken,refreshToken.getToken());
    }

    public String refresh(String refreshToken){
        RefreshToken token = refreshTokenService.verify(refreshToken);
        User user = token.getUser();
        return jwtService.generateToken(user);
    }

    public void logout(String refreshToken){
        RefreshToken token = refreshTokenService.verify(refreshToken);
        refreshTokenService.deleteByUser(token.getUser());
    }

    public void register(String email,String name, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(email, name, passwordEncoder.encode(password), Role.USER);
        userRepository.save(user);
    }
}
