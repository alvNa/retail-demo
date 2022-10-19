package com.inditex.demo.repository;

import com.inditex.demo.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PriceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository repository;

    @Test
    void shouldFindPricesOk() {
        Long productId = 1L;
        Long brandId = 1L;
        var date = LocalDateTime.of(2022,10,1,0,0);
        var dateFrom = LocalDateTime.of(2022,9,1,0,0,1);
        var dateTo = LocalDateTime.of(2022,12,1,0,0,1);

        var price = Price.builder()
                .priceFeeId(1L)
                .brandId(brandId)
                .productId(productId)
                .startDate(dateFrom)
                .endDate(dateTo)
                .build();
        repository.save(price);

        var maybeResult = repository.findByFilter(productId, brandId, date);
        Assertions.assertTrue(maybeResult.isPresent());
        Assertions.assertEquals(maybeResult.get(), price);
    }

    @Test
    void shouldFindPricesKOWhenNoMatchingDate() {
        Long productId = 1L;
        Long brandId = 1L;
        var date = LocalDateTime.of(2021,1,1,0,0);
        var dateFrom = LocalDateTime.of(2022,9,1,0,0,1);
        var dateTo = LocalDateTime.of(2022,12,1,0,0,1);

        var price = Price.builder()
                .priceFeeId(1L)
                .brandId(brandId)
                .productId(productId)
                .startDate(dateFrom)
                .endDate(dateTo)
                .build();
        repository.save(price);

        var maybeResult = repository.findByFilter(productId, brandId, date);
        Assertions.assertFalse(maybeResult.isPresent());
    }
}
