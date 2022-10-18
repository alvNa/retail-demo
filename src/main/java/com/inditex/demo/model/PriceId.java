package com.inditex.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PriceId implements Serializable {

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRICE_FEE_ID")
    private Integer priceFeeId;
}
