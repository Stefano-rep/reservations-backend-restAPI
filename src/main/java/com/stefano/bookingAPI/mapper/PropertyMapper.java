package com.stefano.bookingAPI.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.stefano.bookingAPI.model.dto.CreatePropertyRequest;
import com.stefano.bookingAPI.model.dto.PropertyResponse;
import com.stefano.bookingAPI.model.entity.Property;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property toEntity(CreatePropertyRequest dto);

    PropertyResponse toDto (Property entity);
}
