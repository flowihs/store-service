TRUNCATE TABLE product RESTART IDENTITY CASCADE;

INSERT INTO product (name, description, price, price_type, stock_quantity, category_id, currency, image, sort_order,
                     created_at, updated_at, is_active)
VALUES ('Smartphone', 'Latest model smartphone with advanced features', 699.99, 'PAID', 50, 1, 'USD',
        'images/smartphone.jpg', 1, NOW(), NOW(), TRUE),
       ('Laptop', 'High-performance laptop for gaming and work', 1299.99, 'PAID', 30, 2, 'USD', 'images/laptop.jpg', 2,
        NOW(), NOW(), TRUE),
       ('Headphones', 'Noise-cancelling over-ear headphones', 199.99, 'PAID', 100, 3, 'USD', 'images/headphones.jpg', 3,
        NOW(), NOW(), TRUE),
       ('Smartwatch', 'Stylish smartwatch with fitness tracking', 249.99, 'PAID', 75, 4, 'USD', 'images/smartwatch.jpg',
        4, NOW(), NOW(), TRUE),
       ('Tablet', 'Lightweight tablet with high-resolution display', 499.99, 'PAID', 60, 5, 'USD', 'images/tablet.jpg',
        5, NOW(), NOW(), TRUE),
       ('Bluetooth Speaker', 'Portable Bluetooth speaker with great sound quality', 89.99, 'PAID', 150, 6, 'USD',
        'images/bluetooth_speaker.jpg', 6, NOW(), NOW(), TRUE);