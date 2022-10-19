package com.inditex.demo.service;

import com.inditex.demo.converter.PriceConverter;
import com.inditex.demo.model.Price;
import com.inditex.demo.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PriceServiceTest {
    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceConverter priceConverter;

    @Autowired
    private PriceService priceService;

    @Test
    void shouldFindPriceOK(){
        Long productId = 1L;
        Long brandId = 1L;
        var date = LocalDateTime.of(2022,10,1,0,0);
        var dateFrom = LocalDateTime.of(2022,9,1,0,0,1);
        var dateTo = LocalDateTime.of(2022,12,1,0,0,1);

        var price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setPriceFeeId(1L);
        price.setPrice(BigDecimal.ONE);
        price.setStartDate(dateFrom);
        price.setEndDate(dateTo);

        when(priceRepository.findByFilter(productId, brandId, date)).thenReturn(Optional.of(price));

        var maybeResult = priceService.find(productId,brandId,date);
        Assertions.assertTrue(maybeResult.isPresent());
        Assertions.assertEquals(maybeResult.get().getPrice(), price.getPrice());
        Assertions.assertEquals(maybeResult.get().getStartDate(), price.getStartDate());
    }
}
