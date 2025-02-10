package com.musinsa.backendproject.service;

import com.musinsa.backendproject.dto.LowestHighestPriceGoodsDto;
import com.musinsa.backendproject.dto.LowestPriceBrandGoodsDto;
import com.musinsa.backendproject.dto.LowestPriceGoodsDto;
import com.musinsa.backendproject.dto.response.LowestHighestPriceGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceBrandGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceGoodsResponse;
import com.musinsa.backendproject.exception.DatabaseException;
import com.musinsa.backendproject.model.entity.Goods;
import com.musinsa.backendproject.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final GoodsRepository goodsRepository;

    public Mono<LowestPriceGoodsResponse> getLowestPriceGoods() {
        return goodsRepository.findLowestPriceGoodsByAllCategories()
                .map(goods -> LowestPriceGoodsDto.builder()
                        .category(goods.getCategory())
                        .brand(goods.getBrand())
                        .price(goods.getPrice())
                        .build())
                .collectList()
                .map(list -> LowestPriceGoodsResponse.builder()
                        .lowestPriceList(list)
                        .totalPrice(list.stream().map(LowestPriceGoodsDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                        .build())
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }

    public Mono<LowestHighestPriceGoodsResponse> getLowestAndHighestPriceGoods(String category) {
        return goodsRepository.findLowestAndHighestPriceGoodsByCategory(category)
                .map(goods -> LowestHighestPriceGoodsDto.builder()
                        .brand(goods.getBrand())
                        .price(goods.getPrice())
                        .build())
                .sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
                .collectList()
                .map(list -> LowestHighestPriceGoodsResponse.builder()
                                .category(category)
                                .highestPriceList(!list.isEmpty() ? list.subList(0, 1) : List.of())
                                .lowestPriceList(list.size() > 1 ? list.subList(1, 2) : List.of())
                                .build()
                )
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }

    public Mono<LowestPriceBrandGoodsResponse> getLowestPriceBrandGoods() {
        AtomicReference<String> brand = new AtomicReference<>("");

        return goodsRepository.findLowestPriceBrandGoods()
                .doOnNext(goods -> brand.set(goods.getBrand()))
                .map(goods -> LowestPriceBrandGoodsDto.builder().category(goods.getCategory()).price(goods.getPrice()).build())
                .collectList()
                .map(list -> {
                    LowestPriceBrandGoodsResponse.LowestBrand lowestBrand = LowestPriceBrandGoodsResponse.LowestBrand.builder()
                            .brand(brand.get())
                            .category(list)
                            .totalPrice(list.stream().map(LowestPriceBrandGoodsDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                            .build();

                    return LowestPriceBrandGoodsResponse.builder()
                            .lowestBrand(lowestBrand)
                            .build();
                })
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }

    public Mono<String> addGoods(String category, String brand, BigDecimal price, String insOprt) {
        return goodsRepository.save(Goods.builder()
                        .category(category)
                        .brand(brand)
                        .price(price)
                        .insOprt(insOprt)
                        .updOprt(insOprt)
                        .build())
                .map(goods -> "성공")
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }

    public Mono<String> updateGoods(Long id, String category, String brand, BigDecimal price, String updOprt) {
        return goodsRepository.findById(id)
                .switchIfEmpty(Mono.error(new DatabaseException("대상 상품이 존재하지 않습니다.")))
                .map(goods -> {
                    goods.setCategory(category);
                    goods.setBrand(brand);
                    goods.setPrice(price);
                    goods.setUpdOprt(updOprt);
                    goods.setUpdDate(LocalDateTime.now());
                    return goods;
                })
                .flatMap(goodsRepository::save)
                .thenReturn("성공")
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }

    public Mono<String> deleteGoods(Long id) {
        return goodsRepository.findById(id)
                .switchIfEmpty(Mono.error(new DatabaseException("대상 상품이 존재하지 않습니다.")))
                .flatMap(goodsRepository::delete)
                .thenReturn("성공")
                .onErrorMap(DataAccessException.class, throwable -> new DatabaseException("데이터베이스 처리 중 오류가 발생했습니다."));
    }
}
