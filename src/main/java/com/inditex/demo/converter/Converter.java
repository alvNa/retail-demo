package com.inditex.demo.converter;

public interface Converter<T,DTO> {
    T toModel(DTO dto);
    DTO toDto(T model);
}
