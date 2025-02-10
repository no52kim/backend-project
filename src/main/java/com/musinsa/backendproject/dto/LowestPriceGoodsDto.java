package com.musinsa.backendproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class LowestPriceGoodsDto {

    @JsonProperty("카테고리")
    private String category;

    @JsonProperty("브랜드")
    private String brand;

    @JsonProperty("가격")
    private BigDecimal price;
}
