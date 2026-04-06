package com.stefano.bookingAPI.mapper;
import org.mapstruct.Mapper;

import com.stefano.bookingAPI.model.dto.CreatePropertyRequest;
import com.stefano.bookingAPI.model.dto.PropertyResponse;
import com.stefano.bookingAPI.model.entity.Property;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    Property toEntity(CreatePropertyRequest dto);

    PropertyResponse toDto (Property entity);
}
