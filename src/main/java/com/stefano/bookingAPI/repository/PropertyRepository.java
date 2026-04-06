package com.stefano.bookingAPI.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stefano.bookingAPI.model.entity.Property;

public interface PropertyRepository extends JpaRepository<Property,UUID>,JpaSpecificationExecutor<Property>{
    
}
