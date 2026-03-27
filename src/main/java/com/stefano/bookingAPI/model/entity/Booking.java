package com.stefano.bookingAPI.model.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "property_id")
    Property property;

    Date startDate;

    Date endDate;

    @Positive
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    Status status;

}
