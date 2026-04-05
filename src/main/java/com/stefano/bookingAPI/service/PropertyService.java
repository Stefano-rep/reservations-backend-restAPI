package com.stefano.bookingAPI.service;

import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stefano.bookingAPI.config.PropertySpecification;
import com.stefano.bookingAPI.mapper.PropertyMapper;
import com.stefano.bookingAPI.model.dto.CreatePropertyRequest;
import com.stefano.bookingAPI.model.dto.PropertyResponse;
import com.stefano.bookingAPI.model.entity.Property;
import com.stefano.bookingAPI.repository.PropertyRepository;

@Service
public class PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;

    public PropertyService(PropertyRepository repository,PropertyMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<PropertyResponse> findProperties (String city,BigDecimal minPrice,BigDecimal maxPrice,Pageable pageable){
        Specification<Property> spec = Specification.unrestricted();
        if (city != null) {
            spec = spec.and(PropertySpecification.hasCity(city));
        }

        if (minPrice != null) {
            spec = spec.and(PropertySpecification.priceGreaterThanOrEqual(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(PropertySpecification.priceLessThanOrEqual(maxPrice));
        }
        Page<Property> results = repository.findAll(spec, pageable);
        Page<PropertyResponse> properties = results.map(mapper::toDto);
        return properties;
    }

    public void createProperty(CreatePropertyRequest request){
        Property property = mapper.toEntity(request);
        repository.save(property);
    }
}
