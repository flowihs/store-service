CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    first_name   VARCHAR(255),
    phone_number INT,
    roles        VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);

TRUNCATE TABLE users RESTART IDENTITY CASCADE;

INSERT INTO users (id, username, password, email, first_name, phone_number, roles, created_at, updated_at)
VALUES (1, 'john_doe', 'password123', 'john.doe@example.com', 'John', 1234567890, 'ROLE_ADMIN', NOW(), NOW()),
       (2, 'jane_smith', 'password456', 'jane.smith@example.com', 'Jane', 987654321, 'ROLE_USER', NOW(), NOW()),
       (3,'alice_wonder', 'password789', 'alice.wonder@example.com', 'Alice', 555555555, 'ROLE_USER', NOW(), NOW()),
       (4,'bob_builder', 'password101', 'bob.builder@example.com', 'Bob', 111111111, 'ROLE_ADMIN', NOW(), NOW()),
       (5, 'charlie_brown', 'password202', 'charlie.brown@example.com', 'Charlie', 222222222, 'ROLE_USER', NOW(), NOW());
