package com.musinsa.backendproject.dto.response;

import com.musinsa.backendproject.dto.LowestPriceBrandGoodsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LowestPriceBrandGoodsResponse {

    private LowestBrand 최저가;

    @Data
    @Builder
    public static class LowestBrand {
        private String 브랜드;
        private List<LowestPriceBrandGoodsDto> 카테고리;
        private BigDecimal 총액;
    }
}
