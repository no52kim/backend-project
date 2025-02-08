package com.musinsa.backendproject.controller;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.dto.response.LowestPriceBrandGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceGoodsResponse;
import com.musinsa.backendproject.dto.response.HighLowPriceGoodsResponse;
import com.musinsa.backendproject.service.CategoryGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category-goods")
public class CategoryGoodsController {

    private final CategoryGoodsService CategoryGoodsService;

    @GetMapping("/lowest-price-goods")
    public Mono<CommonResponse<LowestPriceGoodsResponse>> getLowestGoods() {
        return CategoryGoodsService.getLowestPriceGoods()
                .map(CommonResponse::success)
                .onErrorResume(e -> Mono.just(CommonResponse.error(300, e.getMessage())));
    }

    @GetMapping("/{category}/high-low-price-goods")
    public Mono<CommonResponse<HighLowPriceGoodsResponse>> getCategoryPriceRange(
            @PathVariable String category) {
        return CategoryGoodsService.getHighLowPriceGoods(category)
                .map(CommonResponse::success)
                .onErrorResume(e -> Mono.just(CommonResponse.error(300, e.getMessage())));
    }

    @GetMapping("/lowest-price-brand-goods")
    public Mono<CommonResponse<LowestPriceBrandGoodsResponse>> getLowestBrandGoods() {
        return CategoryGoodsService.getLowestPriceBrandGoods()
                .map(CommonResponse::success)
                .onErrorResume(e -> Mono.just(CommonResponse.error(300, e.getMessage())));
    }
}
