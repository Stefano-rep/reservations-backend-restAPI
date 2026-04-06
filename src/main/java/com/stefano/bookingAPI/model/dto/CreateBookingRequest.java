package com.stefano.bookingAPI.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CreateBookingRequest {

    @NotBlank
    @NotEmpty
    UUID userId;

    @NotBlank
    @NotEmpty
    UUID propertyId;

    @FutureOrPresent
    LocalDate startDate;

    @Future
    LocalDate endDate;

}
