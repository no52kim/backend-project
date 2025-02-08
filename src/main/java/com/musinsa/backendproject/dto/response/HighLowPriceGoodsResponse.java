package com.musinsa.backendproject.dto.response;

import com.musinsa.backendproject.dto.HighLowPriceGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HighLowPriceGoodsResponse {

    private String 카테고리;
    private List<HighLowPriceGoodsDto> 최저가;
    private List<HighLowPriceGoodsDto> 최고가;
}