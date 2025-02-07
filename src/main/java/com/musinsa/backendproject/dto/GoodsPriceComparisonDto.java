package com.musinsa.backendproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsPriceComparisonDto {
    private String type;
    private String brand;
    private BigDecimal price;
}
