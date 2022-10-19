package com.inditex.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class PriceDto implements Serializable {
    private Long productId;
    private Long brandId;
    private Long priceFeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}

