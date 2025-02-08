package com.musinsa.backendproject.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LowestPriceBrandGoodsDto {

    private String 카테고리;
    private BigDecimal 가격;
}
