package com.stefano.bookingAPI.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.jmx.export.annotation.ManagedResource;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue
    UUID id;

    String title;

    String description;

    String city;

    @Positive
    BigDecimal pricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User owner;

    int maxGuests;

    @OneToMany(mappedBy = "property",fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "property",fetch = FetchType.LAZY)
    private List<Review> reviews;
}
