package com.musinsa.backendproject.controller;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.dto.request.GoodsRequest;
import com.musinsa.backendproject.dto.response.LowestPriceBrandGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestPriceGoodsResponse;
import com.musinsa.backendproject.dto.response.LowestHighestPriceGoodsResponse;
import com.musinsa.backendproject.exception.RequestExceptionHandler;
import com.musinsa.backendproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService CategoryService;

    @GetMapping("/lowest-price-goods")
    public Mono<CommonResponse<LowestPriceGoodsResponse>> getLowestGoods() {
        return CategoryService.getLowestPriceGoods()
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }

    @GetMapping("/{category}/lowest-highest-price-goods")
    public Mono<CommonResponse<LowestHighestPriceGoodsResponse>> getLowestAndHighestPriceGoods(@PathVariable String category) {
        return CategoryService.getLowestAndHighestPriceGoods(category)
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }

    @GetMapping("/lowest-price-brand-goods")
    public Mono<CommonResponse<LowestPriceBrandGoodsResponse>> getLowestBrandGoods() {
        return CategoryService.getLowestPriceBrandGoods()
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }

    @PostMapping("/goods")
    public Mono<CommonResponse<String>> addGoods(@RequestBody GoodsRequest request) {
        return CategoryService.addGoods(request.getCategory(), request.getBrand(), request.getPrice(), request.getInsOprt())
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }

    @PatchMapping("/goods/{id}")
    public Mono<CommonResponse<String>> updateGoods(@PathVariable Long id, @RequestBody GoodsRequest request) {
        return CategoryService.updateGoods(id, request.getCategory(), request.getBrand(), request.getPrice(), request.getInsOprt())
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }

    @DeleteMapping("/goods/{id}")
    public Mono<CommonResponse<String>> deleteGoods(@PathVariable Long id) {
        return CategoryService.deleteGoods(id)
                .map(CommonResponse::success)
                .onErrorResume(RequestExceptionHandler::handleError);
    }
}
