package com.inditex.demo.converter;

import com.inditex.demo.dto.PriceDto;
import com.inditex.demo.model.Price;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PriceConverter extends Converter<Price,PriceDto>{
    PriceConverter INSTANCE = Mappers.getMapper(PriceConverter.class);

    @Override
    @InheritConfiguration
    Price toModel(PriceDto priceDto);

    @Override
    @InheritInverseConfiguration
    PriceDto toDto(Price model);
}
