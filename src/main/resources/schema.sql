CREATE TABLE IF NOT EXISTS product
(
    id bigint NOT NULL AUTO_INCREMENT,
    brand VARCHAR(255),
    price DECIMAL(10),
    category VARCHAR(255),
    PRIMARY KEY (id)
);