--liquibase formatted sql

--changeset vnavesnoj:1
CREATE TABLE category
(
    id           SERIAL PRIMARY KEY,
    parent_id    INT       REFERENCES category (id) ON DELETE SET NULL,
    is_root      BOOLEAN   NOT NULL,
    is_leaf      BOOLEAN   NOT NULL,
    sort_order   INT       NOT NULL,
    level        INT       NOT NULL,
    image        VARCHAR(255),
    is_active    BOOLEAN   NOT NULL,
    translations JSONB     NOT NULL,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP
);