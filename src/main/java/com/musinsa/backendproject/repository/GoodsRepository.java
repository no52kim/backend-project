package com.musinsa.backendproject.repository;

import com.musinsa.backendproject.dto.GoodsPriceComparisonDto;
import com.musinsa.backendproject.model.entity.Goods;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GoodsRepository extends ReactiveCrudRepository<Goods, Long> {

    @Query("SELECT category, brand, price " +
            "FROM (" +
            "    SELECT " +
            "        g.category," +
            "        g.brand," +
            "        g.price," +
            "        ROW_NUMBER() OVER (PARTITION BY g.category ORDER BY g.price, g.brand desc) AS rn" +
            "    FROM goods g" +
            ") sub " +
            "WHERE rn = 1")
    Flux<Goods> findLowestGoodsByAllCategories();

    @Query("SELECT 'MIN' as type, brand, price " +
    "FROM (SELECT brand, price, ROW_NUMBER() OVER (ORDER BY price, brand DESC) AS rn " +
    "      FROM goods WHERE category = :category) " +
    "WHERE rn = 1 " +
    "UNION ALL " +
    "SELECT 'MAX' as type, brand, price " +
    "FROM (SELECT brand, price, ROW_NUMBER() OVER (ORDER BY price DESC, brand DESC) AS rn " +
    "      FROM goods WHERE category = :category) " +
    "WHERE rn = 1")
    Flux<GoodsPriceComparisonDto> findPriceComparisonByCategory(@Param("category") String category);
}
