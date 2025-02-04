package com.musinsa.backendproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/category-product")
public class CategoryProductController {

    @GetMapping("/getCategoryProduct")
    public Mono<String> getCategoryProduct() {
        return Mono.just("CategoryProduct");
    }
}
