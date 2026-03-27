package com.stefano.bookingAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.bookingAPI.model.dto.CreatePropertyRequest;
import com.stefano.bookingAPI.service.PropertyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("property")
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
    

}
