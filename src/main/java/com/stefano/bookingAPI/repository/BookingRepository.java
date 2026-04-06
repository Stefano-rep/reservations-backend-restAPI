package com.stefano.bookingAPI.repository;

import java.util.UUID;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stefano.bookingAPI.model.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<List<Booking>> findByPropertyId(UUID propertyId);
    
    @Query("SELECT CASE WHEN COUNT(b) = 0 THEN true ELSE false END FROM Booking b WHERE b.property.id = :propertyId AND b.startDate < :endDate AND b.endDate > :startDate")    
    Boolean isAvailable(
        UUID propertyId, LocalDate startDate, LocalDate endDate);
}
