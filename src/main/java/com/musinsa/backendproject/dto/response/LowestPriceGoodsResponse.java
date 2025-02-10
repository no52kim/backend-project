package com.musinsa.backendproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.backendproject.dto.LowestPriceGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LowestPriceGoodsResponse {

    @JsonProperty("최저가")
    private List<LowestPriceGoodsDto> lowestPriceList;

    @JsonProperty("총액")
    private BigDecimal totalPrice;
}
