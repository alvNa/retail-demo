package com.inditex.demo.controller;

import com.inditex.demo.dto.PriceDto;
import com.inditex.demo.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.inditex.demo.controller.PriceController.PRICES_PATH;


@RequiredArgsConstructor
@RestController
@RequestMapping(PRICES_PATH)
@Validated
public class PriceController {

    public static final String PRICES_PATH = "/prices";
    public static final String PRODUCT_QUERY_PARAM = "productId";
    public static final String BRAND_QUERY_PARAM = "brandId";
    public static final String DATE_QUERY_PARAM = "date";

    @Autowired
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceDto> findPrice(@RequestParam(value = PRODUCT_QUERY_PARAM) @NotNull Long productId,
                                               @RequestParam(value= BRAND_QUERY_PARAM) @NotNull Long brandId,
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               @RequestParam(value = DATE_QUERY_PARAM) LocalDateTime date) {
        PriceDto price = priceService.find(productId, brandId, date);
        return ResponseEntity.ok(price);
    }
}