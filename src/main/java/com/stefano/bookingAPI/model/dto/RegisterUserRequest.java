package com.stefano.order_management_api.model.dto;

import com.stefano.order_management_api.validation.PasswordConstraint;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @PasswordConstraint(min = 10, max=30, message = "Password must be at least 10 characters long and contain both letters and numbers")
    private String password;
}
