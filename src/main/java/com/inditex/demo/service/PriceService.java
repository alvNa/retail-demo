package com.inditex.demo.service;

import com.inditex.demo.converter.PriceConverter;
import com.inditex.demo.dto.PriceDto;
import com.inditex.demo.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceConverter priceConverter;

    public Optional<PriceDto> find(Long productId, Long brandId, LocalDateTime date) {
        var maybePrice = priceRepository.findByFilter(productId, brandId, date);
        return maybePrice.map(price -> priceConverter.toDto(price));
    }
}
