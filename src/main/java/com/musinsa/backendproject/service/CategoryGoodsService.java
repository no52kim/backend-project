package com.musinsa.backendproject.service;

import com.musinsa.backendproject.dto.HighLowPriceGoodsDto;
import com.musinsa.backendproject.dto.LowestPriceBrandGoodsDto;
import com.musinsa.backendproject.dto.LowestPriceGoodsDto;
import com.musinsa.backendproject.dto.response.HighLowPriceGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceBrandGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceGoodsResponse;
import com.musinsa.backendproject.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CategoryGoodsService {

    private final GoodsRepository goodsRepository;

    public Mono<LowestPriceGoodsResponse> getLowestPriceGoods() {
        return goodsRepository.findLowestPriceGoodsByAllCategories()
                .map(goods -> LowestPriceGoodsDto.builder()
                        .카테고리(goods.getCategory())
                        .브랜드(goods.getBrand())
                        .가격(goods.getPrice())
                        .build())
                .collectList()
                .map(list -> LowestPriceGoodsResponse.builder()
                        .최저가(list)
                        .총액(list.stream().map(LowestPriceGoodsDto::get가격).reduce(BigDecimal.ZERO, BigDecimal::add))
                        .build());
    }

    public Mono<HighLowPriceGoodsResponse> getHighLowPriceGoods(String category) {
        return goodsRepository.findMinMaxPriceGoodsByCategory(category)
                .map(goods -> HighLowPriceGoodsDto.builder()
                        .브랜드(goods.getBrand())
                        .가격(goods.getPrice())
                        .build())
                .collectList()
                .map(list -> {
                    List<HighLowPriceGoodsDto> priceGoodsDtoList = list.stream().sorted((o1, o2) -> o2.get가격().compareTo(o1.get가격())).toList();

                    return HighLowPriceGoodsResponse.builder()
                            .카테고리(category)
                            .최고가(priceGoodsDtoList.subList(0, 1))
                            .최저가(priceGoodsDtoList.subList(1, 2))
                            .build();
                });
    }

    public Mono<LowestPriceBrandGoodsResponse> getLowestPriceBrandGoods() {
        AtomicReference<String> brand = new AtomicReference<>("");

        return goodsRepository.findMinPriceBrandGoods()
                .doOnNext(goods -> brand.set(goods.getBrand()))
                .map(goods -> LowestPriceBrandGoodsDto.builder().카테고리(goods.getCategory()).가격(goods.getPrice()).build())
                .collectList()
                .map(list -> {
                    LowestPriceBrandGoodsResponse.LowestBrand lowestBrand = LowestPriceBrandGoodsResponse.LowestBrand.builder()
                            .브랜드(brand.get())
                            .카테고리(list)
                            .총액(list.stream().map(LowestPriceBrandGoodsDto::get가격).reduce(BigDecimal.ZERO, BigDecimal::add))
                            .build();

                    return LowestPriceBrandGoodsResponse.builder()
                            .최저가(lowestBrand)
                            .build();
                });
    }
}
