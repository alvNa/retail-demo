package com.inditex.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRICES")
public class Price implements Serializable {
    @Id
    @Column(name = "PRICE_ID")
    private Long priceId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "PRICE_FEE_ID")
    private Long priceFeeId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURRENCY")
    private String currency;
}