--liquibase formatted sql

--changeset vnavesnoj:1
CREATE TABLE category
(
    id                  SERIAL PRIMARY KEY,
    slug                VARCHAR(255) NOT NULL UNIQUE,
    parent_id           INT          REFERENCES category (id) ON DELETE SET NULL,
    sort_order          INT          NOT NULL DEFAULT 0,
    level               INT          NOT NULL DEFAULT 0,
    image               VARCHAR(255),
    is_active           BOOLEAN               DEFAULT TRUE,
    translations        JSONB        NOT NULL,
    default_lang_code   VARCHAR(2)   NOT NULL,
    name_default        VARCHAR(255) GENERATED ALWAYS AS (translations #>> ARRAY [default_lang_code, 'name']) STORED,
    description_default TEXT GENERATED ALWAYS AS (translations #>> ARRAY [default_lang_code, 'description']) STORED,
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP
);