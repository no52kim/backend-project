package com.musinsa.backendproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LowestPriceBrandGoodsDto {
    
    @JsonProperty("카테고리")
    private String category;
    
    @JsonProperty("가격")
    private BigDecimal price;
}
