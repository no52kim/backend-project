package com.musinsa.backendproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Table("goods")
public class Goods {
    @Id
    private Long id;
    private String brand;
    private BigDecimal price;
    private String category;

    @CreatedDate
    private LocalDateTime insDate;
    private String insOprt;

    @LastModifiedDate
    private LocalDateTime updDate;
    private String updOprt;
}
