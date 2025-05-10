--liquibase formatted sql

--changeset flowihs:1
CREATE TABLE product
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    TEXT         NOT NULL,
    price          DECIMAL(10, 2),
    price_type     VARCHAR(31),
    stock_quantity INT          NOT NULL,
    category_id    INT          NOT NULL,
    currency       VARCHAR(15)  NOT NULL,
    image          VARCHAR(511) NOT NULL,
    sort_order     INT          NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP,
    is_active      BOOLEAN      NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);