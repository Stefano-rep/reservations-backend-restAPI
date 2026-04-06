package com.stefano.bookingAPI.mapper;

import org.mapstruct.Mapper;

import com.stefano.bookingAPI.model.dto.CreateBookingRequest;
import com.stefano.bookingAPI.model.entity.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(CreateBookingRequest dto);
}
