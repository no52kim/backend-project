package com.musinsa.backendproject.repository;

import com.musinsa.backendproject.model.entity.Goods;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

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
    Flux<Goods> findLowestPriceGoodsByAllCategories();

    @Query("SELECT category, brand, price " +
    "FROM (SELECT category, brand, price, ROW_NUMBER() OVER (ORDER BY price, brand DESC) AS rn " +
    "      FROM goods WHERE category = :category) " +
    "WHERE rn = 1 " +
    "UNION ALL " +
    "SELECT category, brand, price " +
    "FROM (SELECT category, brand, price, ROW_NUMBER() OVER (ORDER BY price DESC, brand DESC) AS rn " +
    "      FROM goods WHERE category = :category) " +
    "WHERE rn = 1")
    Flux<Goods> findLowestAndHighestPriceGoodsByCategory(@Param("category") String category);

    @Query("WITH CategoryMinPrices AS ( " +
            "    SELECT brand, category, MIN(price) as min_price " +
            "    FROM goods " +
            "    GROUP BY brand, category), "+
            "BrandTotals AS ( " +
            "    SELECT brand, " +
            "           SUM(min_price) as total_price, " +
            "           COUNT(DISTINCT category) as category_count " +
            "    FROM CategoryMinPrices " +
            "    GROUP BY brand " +
            "    HAVING COUNT(DISTINCT category) = (SELECT COUNT(DISTINCT category) FROM goods)), " +
            "MinBrand AS ( " +
            "    SELECT brand " +
            "    FROM BrandTotals " +
            "    WHERE total_price = (SELECT MIN(total_price) FROM BrandTotals)) " +
            "SELECT cmp.category, " +
            "       cmp.brand, " +
            "       cmp.min_price as price " +
            "FROM CategoryMinPrices cmp " +
            "WHERE cmp.brand = (SELECT brand FROM MinBrand) " +
            "ORDER BY cmp.category")
    Flux<Goods> findLowestPriceBrandGoods();
}
