package com.inditex.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.demo.dto.PriceDto;
import com.inditex.demo.http.RestResponseEntityExceptionHandler;
import com.inditex.demo.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.inditex.demo.controller.PriceController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({PriceController.class})
@Import(RestResponseEntityExceptionHandler.class)
public class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindOK() throws Exception {
        Long productId = 1L;
        Long brandId = 1L;
        var date = LocalDateTime.of(2022,10,1,0,0);
        var dateFrom = LocalDateTime.of(2022,9,1,0,0,1);
        var dateTo = LocalDateTime.of(2022,12,1,0,0,1);
        var priceDto = PriceDto.builder()
                .productId(productId)
                .brandId(brandId)
                .priceFeeId(1L)
                .price(BigDecimal.ONE)
                .startDate(dateFrom)
                .endDate(dateTo)
                .build();

        when(priceService.find(eq(productId), eq(brandId), any(LocalDateTime.class)))
                .thenReturn(Optional.of(priceDto));

        mockMvc.perform(get(PRICES_PATH)
                        .param(PRODUCT_QUERY_PARAM, productId.toString())
                        .param(BRAND_QUERY_PARAM, brandId.toString())
                        .param(DATE_QUERY_PARAM, date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").value(dateFrom.toString()))
                .andExpect(jsonPath("$.endDate").value(dateTo.toString()));
    }

    @Test
    void shouldFindKOWhenNoInput() throws Exception {
        mockMvc.perform(get(PRICES_PATH))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFindKOWhenNoProduct() throws Exception {
        Long brandId = 1L;
        var date = LocalDateTime.of(2022,10,1,0,0);
        mockMvc.perform(get(PRICES_PATH)
                        .param(BRAND_QUERY_PARAM, brandId.toString())
                        .param(DATE_QUERY_PARAM, date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFindKOWhenNoBrand() throws Exception {
        Long productId = 1L;
        var date = LocalDateTime.of(2022,10,1,0,0);
        mockMvc.perform(get(PRICES_PATH)
                        .param(PRODUCT_QUERY_PARAM, productId.toString())
                        .param(DATE_QUERY_PARAM, date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFindKOWhenNoDate() throws Exception {
        Long productId = 1L;
        Long brandId = 1L;
        mockMvc.perform(get(PRICES_PATH)
                        .param(PRODUCT_QUERY_PARAM, productId.toString())
                        .param(BRAND_QUERY_PARAM, brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}