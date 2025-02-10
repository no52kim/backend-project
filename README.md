# 무신사 백엔드 과제

## 구현 범위

### 1. REST API 구현
- 카테고리별 최저가격인 브랜드와 상품 가격과 총액 조회
- 단일 브랜드로 전체 카테고리 구매 시 최저가 브랜드와 상품 가격 조회
- 특정 카테고리에서 최저/최고가 브랜드 및 가격 조회
- 상품 추가/수정/삭제 API

### 2. 기술 스택
- Java 23
- Spring Boot 3.4.2
- Spring WebFlux
- R2DBC
- H2 Database
- Gradle
- lombok

### 3. 주요 포인트
1. Reactive Programming을 활용한 비동기 처리를 사용하였습니다. 작은 서비스라면 러닝 커브 및 익숙하여 개발 속도가 빠른 Spring MVC를 사용해서 빠르게 서비스를 구축 후, 트래픽에 따라 필요한 만큼 서버 리소스를 Scale-out하는 방식이 더욱 좋겠지만 무신사와 같은 업계 최상위의 트래픽을 처리해야하는 서비스에서는 개발 단계에서부터 트래픽 처리에 대한 고려가 필요하다고 생각합니다. 따라서, 개발 공수가 더 들더라도 적은 리소스로 효율적인 트래픽 처리가 가능한 Spring Webflux를 적용하여 개발했습니다.
2. 간단한 Exception Handler 및 에러 이벤트 처리를 통해 Database 에러와 일반 Exception 처리를 구분하여 Front에서 에러 케이스를 인지할 수 있도록 하였습니다.
3. Blocking으로 동작하는 JPA를 사용하지않고 Non-blocking을 지원하는 R2DBC를 통해 동적 쿼리를 사용하여 Repository 계층을 구현하였습니다.
4. GOODS 테이블과 CATEGORY 테이블을 분리하여 CATEGORY 테이블의 category (카테고리명)와 goods 테이블의 category에 fk를 설정하여 상품 추가/수정 시 잘못된 카테고리가 입력된 경우를 방지하였습니다.

## 빌드 및 실행 방법

### 빌드 환경
- JDK 23 이상
- Gradle 8.x 이상

### 빌드
빌드
```
./gradlew clean build
```

애플리케이션 실행
```
./gradlew bootRun
```

## API 명세

### 1. 전체 카테고리 최저가 조회
```
GET /api/category/lowest-price-goods
```

### 2. 카테고리별 최고/최저가 조회
```
GET /api/category/{category}/high-low-price-goods
```

### 3. 브랜드별 카테고리 최저가 합산 조회
```
GET /api/category/lowest-price-brand-goods
```

### 4. 상품 추가
```
POST /api/category/goods
```

### 5. 상품 수정
```
PUT /api/category/goods/{id}
```

### 6. 상품 삭제
```
DELETE /api/category/goods/{id}
```

## 데이터베이스 스키마
```
CREATE TABLE IF NOT EXISTS category (
    category VARCHAR(255) NOT NULL,
    PRIMARY KEY (category)
);

CREATE TABLE IF NOT EXISTS goods
(
    id bigint NOT NULL AUTO_INCREMENT,
    brand VARCHAR(255),
    price DECIMAL(10),
    category VARCHAR(255),
    ins_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ins_oprt VARCHAR(255),
    upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    upd_oprt VARCHAR(255),
    PRIMARY KEY (id),

    CONSTRAINT FK_GOODS_CATEGORY FOREIGN KEY (category) REFERENCES category(category)
);
```