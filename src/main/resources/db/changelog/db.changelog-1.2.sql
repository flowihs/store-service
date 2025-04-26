--liquibase formatted sql
--changeset zaraza:1
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    username     VARCHAR(100) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    first_name   VARCHAR(100) NOT NULL ,
    phone_number VARCHAR(31) NOT NULL ,
    roles        VARCHAR(64)[] NOT NULL,
    created_at   TIMESTAMP NOT NULL ,
    updated_at   TIMESTAMP
);