package com.stefano.bookingAPI.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePropertyRequest {
    @NotBlank
    private String title;

    private String description;
    @NotBlank
    private String city;
    @NotBlank
    @Positive
    private BigDecimal pricePerNight;
}
