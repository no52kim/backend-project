package com.musinsa.backendproject.dto.response;

import com.musinsa.backendproject.dto.LowestGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class GetLowestGoodsResponse {
    private List<LowestGoodsDto> 최저가;
    private BigDecimal 총액;
}
