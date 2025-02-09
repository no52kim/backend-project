package com.musinsa.backendproject.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsRequest {
    private String category;
    private String brand;
    private BigDecimal price;
    private String insOprt;
}
