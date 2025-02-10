package com.musinsa.backendproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.backendproject.dto.LowestPriceBrandGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LowestPriceBrandGoodsResponse {

    @JsonProperty("최저가")
    private LowestBrand lowestBrand;

    @Data
    @Builder
    public static class LowestBrand {

        @JsonProperty("브랜드")
        private String brand;

        @JsonProperty("카테고리")
        private List<LowestPriceBrandGoodsDto> category;

        @JsonProperty("총액")
        private BigDecimal totalPrice;
    }
}
