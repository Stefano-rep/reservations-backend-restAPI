package com.stefano.bookingAPI.service;

import com.stefano.bookingAPI.model.dto.CreateBookingRequest;
import com.stefano.bookingAPI.mapper.BookingMapper;
import com.stefano.bookingAPI.model.entity.Booking;
import com.stefano.bookingAPI.repository.BookingRepository;
import com.stefano.bookingAPI.repository.PropertyRepository;
import com.stefano.bookingAPI.model.entity.Property;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final PropertyRepository propertyRepository;
    private final BookingMapper mapper;

    public BookingService(BookingRepository repository, PropertyRepository propertyRepository, BookingMapper mapper) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
        this.mapper = mapper;
    }

    public void createBooking(CreateBookingRequest request){
        if(request.getStartDate().isAfter(request.getEndDate())){
            throw new IllegalArgumentException("Start date must be before end date");   
        }
        if (!repository.isAvailable(request.getPropertyId(), request.getStartDate(), request.getEndDate())) {
            throw new IllegalArgumentException("Selected dates not available for this property");
        }
        else{
            Booking booking = mapper.toEntity(request);
            Property property = propertyRepository.findById(request.getPropertyId()).orElseThrow(() -> new IllegalArgumentException("Property not found"));
            BigDecimal pricePerNight = property.getPricePerNight();
            long nights = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
            booking.setTotalPrice(pricePerNight.multiply(BigDecimal.valueOf(nights)));
            repository.save(booking);
        }
    }
}
