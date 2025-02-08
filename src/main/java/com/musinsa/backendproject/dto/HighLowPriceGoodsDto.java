package com.musinsa.backendproject.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class HighLowPriceGoodsDto {
    
    private String 브랜드;
    private BigDecimal 가격;
}
