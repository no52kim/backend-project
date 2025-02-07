package com.musinsa.backendproject.service;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.dto.LowestGoodsDto;
import com.musinsa.backendproject.dto.response.GetGoodsPriceComparisonResponse;
import com.musinsa.backendproject.dto.response.GetLowestGoodsResponse;
import com.musinsa.backendproject.model.entity.Goods;
import com.musinsa.backendproject.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryGoodsServiceImpl implements CategoryGoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    public Mono<GetLowestGoodsResponse> getLowestGoods() {
        return goodsRepository.findLowestGoodsByAllCategories()
                .map(goods -> new LowestGoodsDto(goods.getCategory(), goods.getBrand(), goods.getPrice()))
                .collectList()
                .map(list -> GetLowestGoodsResponse.builder()
                        .최저가(list)
                        .총액(list.stream().map(LowestGoodsDto::get가격).reduce(BigDecimal.ZERO, BigDecimal::add))
                        .build());
    }

    @Override
    public Mono<GetGoodsPriceComparisonResponse> getGoodsPriceComparison(String category) {
        return null;
    }
}
