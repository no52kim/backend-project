package com.musinsa.backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class LowestPriceGoodsDto {

    private String 카테고리;
    private String 브랜드;
    private BigDecimal 가격;
}
