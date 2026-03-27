package com.stefano.bookingAPI.config;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.stefano.bookingAPI.model.entity.Property;

public class PropertySpecification {
    public static Specification<Property> hasCity(String city){
        return (root,query,cb) -> city == null ? null : cb.equal(root.get("city"),city);
    }

    public static Specification<Property> priceBetween(BigDecimal min, BigDecimal max){
        return (root,query,cb) -> {
            if(min == null && max == null) return null;

            if (min != null && max != null) return cb.between(root.get("pricePerNight"), min, max);

            if (min != null) return cb.greaterThanOrEqualTo(root.get("pricePerNight"), min);

            return cb.lessThanOrEqualTo(root.get("pricePerNight"), max);
        };
    }
}
