package com.musinsa.backendproject.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class GetGoodsPriceComparisonResponse {

    private String 카테고리;
    private List<PriceDetail> 최저가;
    private List<PriceDetail> 최고가;

    @Data
    @Builder
    public static class PriceDetail {
        private String 브랜드;
        private BigDecimal 가격;
    }
}