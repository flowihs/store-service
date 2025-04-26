CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    first_name   VARCHAR(255),
    phone_number VARCHAR(255),
    roles        VARCHAR(64)[] NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);