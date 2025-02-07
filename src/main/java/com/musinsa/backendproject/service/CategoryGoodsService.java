package com.musinsa.backendproject.service;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.dto.LowestGoodsDto;
import com.musinsa.backendproject.dto.response.GetGoodsPriceComparisonResponse;
import com.musinsa.backendproject.dto.response.GetLowestGoodsResponse;
import com.musinsa.backendproject.model.entity.Goods;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryGoodsService {

    Mono<GetLowestGoodsResponse> getLowestGoods();
    Mono<GetGoodsPriceComparisonResponse> getGoodsPriceComparison(String category);
}
