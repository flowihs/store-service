TRUNCATE TABLE users RESTART IDENTITY CASCADE;

INSERT INTO users (username, password, email, first_name, phone_number, roles, created_at, updated_at)
VALUES ('john_doe', 'password123', 'john.doe@example.com', 'John', '123-456-7890', '{"ROLE_USER"}', NOW(), NOW()),
       ('jane_smith', 'securepassword', 'jane.smith@example.com', 'Jane', '098-765-4321', '{"ROLE_ADMIN", "ROLE_USER"}',
        NOW(), NOW()),
       ('alice_jones', 'mypassword', 'alice.jones@example.com', 'Alice', '555-555-5555', '{"ROLE_USER"}', NOW(), NOW()),
       ('bob_brown', 'bobpassword', 'bob.brown@example.com', 'Bob', '444-444-4444', '{"ROLE_USER", "ROLE_MODERATOR"}',
        NOW(), NOW());