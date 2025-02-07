package com.musinsa.backendproject.dto;

import com.musinsa.backendproject.model.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class LowestGoodsDto {

    private String 카테고리;
    private String 브랜드;
    private BigDecimal 가격;

    public static LowestGoodsDto of(Goods goods) {
        return LowestGoodsDto.builder()
                .카테고리(goods.getCategory())
                .브랜드(goods.getBrand())
                .가격(goods.getPrice())
                .build();
    }
}
