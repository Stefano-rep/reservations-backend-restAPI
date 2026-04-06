package com.stefano.bookingAPI.controller;

import com.stefano.bookingAPI.model.dto.CreateBookingRequest;
import com.stefano.bookingAPI.service.BookingService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<Void> postMethodName(@RequestBody CreateBookingRequest request) {
        bookingService.createBooking(request);
        return ResponseEntity.ok().build();
    }
    

}
