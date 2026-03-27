package com.stefano.bookingAPI.model.entity;

import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    Property property;

    @Max(5)
    @Min(1)
    int rating;

    String comment;
}
