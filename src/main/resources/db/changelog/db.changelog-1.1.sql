--liquibase formatted sql

--changeset flowihs:1
CREATE TABLE product
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(255)   NOT NULL,
    description    TEXT,
    price          DECIMAL(10, 2),
    price_type     VARCHAR(31),
    stock_quantity INT            NOT NULL,
    category_id    INT            NOT NULL,
    currency       VARCHAR(15),
    image          VARCHAR(511),
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP,
    is_active      BOOLEAN,
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);