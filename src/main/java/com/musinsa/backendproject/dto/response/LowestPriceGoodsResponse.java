package com.musinsa.backendproject.dto.response;

import com.musinsa.backendproject.dto.LowestPriceGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LowestPriceGoodsResponse {
    private List<LowestPriceGoodsDto> 최저가;
    private BigDecimal 총액;
}
