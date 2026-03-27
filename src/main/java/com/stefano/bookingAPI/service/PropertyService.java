package com.stefano.bookingAPI.service;

import java.math.BigDecimal;
import java.util.List;


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

    public List<PropertyResponse> findProperties (String city,BigDecimal minPrice,BigDecimal maxPrice){
        Specification<Property> spec = Specification.where(PropertySpecification.hasCity(city))
        .and(PropertySpecification.priceBetween(minPrice, maxPrice));
        List<Property> results = repository.findAll(spec);
        List<PropertyResponse> properties = results.stream().map(mapper::toDto).toList();
        return properties;
    }

    public void createProperty(CreatePropertyRequest request){
        Property property = mapper.toEntity(request);
        repository.save(property);
    }
}
