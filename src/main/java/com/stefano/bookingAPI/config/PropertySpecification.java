package com.stefano.bookingAPI.config;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.stefano.bookingAPI.model.entity.Property;

public class PropertySpecification {
    public static Specification<Property> hasCity(String city){
        return (root,query,cb) -> city == null ? null : cb.equal(root.get("city"),city);
    }

    public static Specification<Property> priceGreaterThanOrEqual(BigDecimal min){
        return (root,query,cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("pricePerNight"), min);
    }

    public static Specification<Property> priceLessThanOrEqual(BigDecimal max){
        return (root,query,cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("pricePerNight"), max);
    }
}
