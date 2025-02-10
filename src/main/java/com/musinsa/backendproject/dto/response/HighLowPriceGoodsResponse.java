package com.musinsa.backendproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.backendproject.dto.HighLowPriceGoodsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HighLowPriceGoodsResponse {

    @JsonProperty("카테고리")
    private String category;

    @JsonProperty("최저가")
    private List<HighLowPriceGoodsDto> lowPriceList;

    @JsonProperty("최고가")
    private List<HighLowPriceGoodsDto> highPriceList;
}