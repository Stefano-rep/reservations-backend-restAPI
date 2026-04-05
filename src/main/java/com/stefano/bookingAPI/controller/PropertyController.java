package com.stefano.bookingAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.bookingAPI.model.dto.CreatePropertyRequest;
import com.stefano.bookingAPI.service.PropertyService;
import com.stefano.bookingAPI.model.dto.PropertyResponse;
import java.math.BigDecimal;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("properties")
public class PropertyController {
    private final PropertyService service;

    public PropertyController(PropertyService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProperty(@RequestBody CreatePropertyRequest request) {
        service.createProperty(request);
        return ResponseEntity.ok(null);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<PropertyResponse>> getMethodName(
        @RequestParam(required = false) String city, 
        @RequestParam(required = false) BigDecimal minPrice, 
        @RequestParam(required = false) BigDecimal maxPrice,Pageable pageable) {
        return ResponseEntity.ok(service.findProperties(city, minPrice, maxPrice, pageable));
    }

}
