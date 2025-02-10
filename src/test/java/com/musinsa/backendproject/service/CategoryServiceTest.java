package com.musinsa.backendproject.service;

import com.musinsa.backendproject.exception.DatabaseException;
import com.musinsa.backendproject.model.entity.Goods;
import com.musinsa.backendproject.repository.GoodsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        Mockito.reset(goodsRepository);
    }

    @Test
    void testGetLowestPriceGoods() {
        List<Goods> mockGoodsList = new ArrayList<>();

        mockGoodsList.add(Goods.builder()
                .category("가방")
                .brand("A")
                .price(BigDecimal.valueOf(2000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("상의")
                .brand("C")
                .price(BigDecimal.valueOf(10000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("바지")
                .brand("D")
                .price(BigDecimal.valueOf(3000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("모자")
                .brand("D")
                .price(BigDecimal.valueOf(1500))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("아우터")
                .brand("E")
                .price(BigDecimal.valueOf(5000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("액세서리")
                .brand("F")
                .price(BigDecimal.valueOf(1900))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("스니커즈")
                .brand("G")
                .price(BigDecimal.valueOf(9000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("양말")
                .brand("I")
                .price(BigDecimal.valueOf(1700))
                .build());

        Mockito.when(goodsRepository.findLowestPriceGoodsByAllCategories())
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestPriceGoods())
                .expectNextMatches(response ->
                    response.getLowestPriceList().size() == 8
                        && response.getTotalPrice().compareTo(BigDecimal.valueOf(34100)) == 0
                )
                .verifyComplete();
    }

    @Test
    void testGetLowestPriceGoods_NotFound() {
        List<Goods> mockGoodsList = new ArrayList<>();

        Mockito.when(goodsRepository.findLowestPriceGoodsByAllCategories())
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestPriceGoods())
                .expectNextMatches(response ->
                        response.getLowestPriceList().isEmpty()
                                && response.getTotalPrice().compareTo(BigDecimal.valueOf(0)) == 0
                )
                .verifyComplete();
    }

    @Test
    void testGetLowestAndHighestPriceGoods() {
        List<Goods> mockGoodsList = new ArrayList<>();

        mockGoodsList.add(Goods.builder()
                .category("가방")
                .brand("A")
                .price(BigDecimal.valueOf(2000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("가방")
                .brand("C")
                .price(BigDecimal.valueOf(10000))
                .build());

        Mockito.when(goodsRepository.findLowestAndHighestPriceGoodsByCategory("가방"))
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestAndHighestPriceGoods("가방"))
                .expectNextMatches(response ->
                        response.getCategory().equals("가방")
                                && response.getLowestPriceList().size() == 1
                                && response.getHighestPriceList().size() == 1
                                && (response.getLowestPriceList().getFirst().getBrand().equals("A") && Objects.equals(response.getLowestPriceList().getFirst().getPrice(), BigDecimal.valueOf(2000)))
                                && (response.getHighestPriceList().getFirst().getBrand().equals("C") && Objects.equals(response.getHighestPriceList().getFirst().getPrice(), BigDecimal.valueOf(10000)))
                )
                .verifyComplete();
    }

    @Test
    void testGetLowestAndHighestPriceGoods_NotFound() {
        List<Goods> mockGoodsList = new ArrayList<>();

        Mockito.when(goodsRepository.findLowestAndHighestPriceGoodsByCategory("가방"))
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestAndHighestPriceGoods("가방"))
                .expectNextMatches(response ->
                        response.getCategory().equals("가방")
                                && response.getLowestPriceList().isEmpty()
                                && response.getHighestPriceList().isEmpty()
                )
                .verifyComplete();
    }

    @Test
    void testGetLowestPriceBrandGoods() {
        List<Goods> mockGoodsList = new ArrayList<>();

        mockGoodsList.add(Goods.builder()
                .category("가방")
                .brand("D")
                .price(BigDecimal.valueOf(2500))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("상의")
                .brand("D")
                .price(BigDecimal.valueOf(10100))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("바지")
                .brand("D")
                .price(BigDecimal.valueOf(3000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("모자")
                .brand("D")
                .price(BigDecimal.valueOf(1500))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("아우터")
                .brand("D")
                .price(BigDecimal.valueOf(5100))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("액세서리")
                .brand("D")
                .price(BigDecimal.valueOf(2000))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("스니커즈")
                .brand("D")
                .price(BigDecimal.valueOf(9500))
                .build());

        mockGoodsList.add(Goods.builder()
                .category("양말")
                .brand("D")
                .price(BigDecimal.valueOf(2400))
                .build());

        Mockito.when(goodsRepository.findLowestPriceBrandGoods())
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestPriceBrandGoods())
                .expectNextMatches(response ->
                        response.getLowestBrand().getBrand().equals("D")
                                && response.getLowestBrand().getCategory().size() == 8
                                && response.getLowestBrand().getTotalPrice().compareTo(BigDecimal.valueOf(36100)) == 0
                )
                .verifyComplete();
    }

    @Test
    void testGetLowestPriceBrandGoods_NotFound() {
        List<Goods> mockGoodsList = new ArrayList<>();

        Mockito.when(goodsRepository.findLowestPriceBrandGoods())
                .thenReturn(Flux.fromIterable(mockGoodsList));

        StepVerifier.create(categoryService.getLowestPriceBrandGoods())
                .expectNextMatches(response ->
                        response.getLowestBrand().getBrand().isEmpty()
                                && response.getLowestBrand().getCategory().isEmpty()
                                && response.getLowestBrand().getTotalPrice().compareTo(BigDecimal.valueOf(0)) == 0
                )
                .verifyComplete();
    }

    @Test
    void testAddGoods() {
        Goods savedGoods = Goods.builder()
                .id(1L)
                .category("가방")
                .brand("A")
                .price(BigDecimal.valueOf(2000))
                .build();

        Mockito.when(goodsRepository.save(any(Goods.class)))
                .thenReturn(Mono.just(savedGoods));

        StepVerifier.create(categoryService.addGoods("가방", "A", BigDecimal.valueOf(20000), "tester"))
                .expectNext("성공")
                .verifyComplete();
    }

    @Test
    void testUpdateGoods() {
        Goods existingGoods = Goods.builder().id(1L).category("가방").brand("B").price(BigDecimal.valueOf(3000)).build();
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Mono.just(existingGoods));

        Goods updatingGoods = Goods.builder()
                .id(1L)
                .category("가방")
                .brand("A")
                .price(BigDecimal.valueOf(2000))
                .build();

        Mockito.when(goodsRepository.save(any(Goods.class)))
                .thenReturn(Mono.just(updatingGoods));

        StepVerifier.create(categoryService.updateGoods(1L, "가방", "A", BigDecimal.valueOf(2000), "tester"))
                .expectNext("성공")
                .verifyComplete();
    }

    @Test
    void testUpdateGoods_NotFound() {
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Mono.empty());

        Goods updatingGoods = Goods.builder()
                .id(1L)
                .category("가방")
                .brand("A")
                .price(BigDecimal.valueOf(2000))
                .build();

        StepVerifier.create(categoryService.updateGoods(1L, "가방", "A", BigDecimal.valueOf(2000), "tester"))
                .expectErrorMatches(throwable ->
                        throwable instanceof DatabaseException
                                && throwable.getMessage().contains("대상 상품이 존재하지 않습니다.")
                )
                .verify();
    }

    @Test
    void testDeleteGoods_NotFound() {
        Mockito.when(goodsRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(categoryService.deleteGoods(1L))
                .expectErrorMatches(throwable ->
                        throwable instanceof DatabaseException
                                && throwable.getMessage().contains("대상 상품이 존재하지 않습니다.")
                )
                .verify();
    }

    @Test
    void testDeleteGoods_Success() {
        Goods existingGoods = Goods.builder().id(1L).build();
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Mono.just(existingGoods));
        Mockito.when(goodsRepository.delete(existingGoods)).thenReturn(Mono.empty());

        StepVerifier.create(categoryService.deleteGoods(1L))
                .expectNext("성공")
                .verifyComplete();
    }
}