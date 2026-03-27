package com.stefano.bookingAPI.model.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PropertyResponse {
    private String title;

    private String description;

    private String city;

    private BigDecimal pricePerNight;
}
