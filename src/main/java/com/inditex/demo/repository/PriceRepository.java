package com.inditex.demo.repository;

import com.inditex.demo.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId= :brandId AND " +
            ":date BETWEEN p.startDate AND p.endDate")
    Price findByFilter(@Param("productId") Long productId, @Param("brandId")Long brandId, @Param("date")LocalDateTime date);
}
