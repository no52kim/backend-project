package com.musinsa.backendproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LowestHighestPriceGoodsDto {

    @JsonProperty("브랜드")
    private String brand;

    @JsonProperty("가격")
    private BigDecimal price;
}
