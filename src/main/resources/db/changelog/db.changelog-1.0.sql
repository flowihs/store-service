--liquibase formatted sql

--changeset vnavesnoj:1
CREATE TABLE category
(
    id                SERIAL PRIMARY KEY,
    slug              VARCHAR(255) NOT NULL UNIQUE,
    parent_id         INT          REFERENCES category (id) ON DELETE SET NULL,
    sort_order        INT          NOT NULL,
    level             INT          NOT NULL,
    image             VARCHAR(255),
    is_active         BOOLEAN,
    translations      JSONB        NOT NULL,
    default_lang_code VARCHAR(7)   NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP
);