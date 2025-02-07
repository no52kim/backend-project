package com.musinsa.backendproject.controller;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.dto.response.GetLowestGoodsResponse;
import com.musinsa.backendproject.model.entity.Goods;
import com.musinsa.backendproject.service.CategoryGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category-goods")
public class CategoryGoodsController {

    private final CategoryGoodsService categoryGoodsService;

    @GetMapping("/get-lowest-goods")
    public Mono<CommonResponse<GetLowestGoodsResponse>> getLowestGoods() {
        return categoryGoodsService.getLowestGoods()
                .map(CommonResponse::success)
                .onErrorResume(e -> Mono.just(CommonResponse.error(300, e.getMessage())));
    }
}
