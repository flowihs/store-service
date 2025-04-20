-- Очистка таблицы (если нужно)
TRUNCATE TABLE category RESTART IDENTITY CASCADE;

-- Вставка корневых категорий (level=0, is_root=true)
INSERT INTO category (id,parent_id, is_root, is_leaf, sort_order, level, is_active, translations, created_at)
VALUES
    -- 1. Электроника (корневая)
    (1,NULL, true, false, 1, 0, true,
     '{"en": {"name": "Electronics", "description": "All electronic devices"},
       "ru": {"name": "Электроника", "description": "Все электронные устройства"}}',
     NOW()),

    -- 2. Одежда (корневая)
    (2,NULL, true, false, 2, 0, true,
     '{"en": {"name": "Clothing", "description": "Men and women clothing"},
       "ru": {"name": "Одежда", "description": "Мужская и женская одежда"}}',
     NOW()),

    -- 3. Книги (корневая)
    (3,NULL, true, false, 3, 0, true,
     '{"en": {"name": "Books", "description": "Fiction and non-fiction"},
       "ru": {"name": "Книги", "description": "Художественная и научная литература"}}',
     NOW());

-- Вставка подкатегорий 1-го уровня (level=1, is_root=false)
INSERT INTO category (id,parent_id, is_root, is_leaf, sort_order, level, is_active, translations, created_at)
VALUES
    -- 4. Смартфоны (дочерняя для Электроники)
    (4,1, false, false, 1, 1, true,
     '{"en": {"name": "Smartphones", "description": "Latest models"},
       "ru": {"name": "Смартфоны", "description": "Последние модели"}}',
     NOW()),

    -- 5. Ноутбуки (дочерняя для Электроники)
    (5,1, false, false, 2, 1, true,
     '{"en": {"name": "Laptops", "description": "For work and gaming"},
       "ru": {"name": "Ноутбуки", "description": "Для работы и игр"}}',
     NOW()),

    -- 6. Мужская одежда (дочерняя для Одежды)
    (6,2, false, false, 1, 1, true,
     '{"en": {"name": "Men''s Clothing", "description": "Shirts, pants, etc."},
       "ru": {"name": "Мужская одежда", "description": "Рубашки, брюки и т.д."}}',
     NOW()),

    -- 7. Художественная литература (дочерняя для Книг)
    (7,3, false, false, 1, 1, true,
     '{"en": {"name": "Fiction", "description": "Novels and stories"},
       "ru": {"name": "Художественная литература", "description": "Романы и рассказы"}}',
     NOW());

-- Вставка листовых категорий 2-го уровня (level=2, is_leaf=true)
INSERT INTO category (id,parent_id, is_root, is_leaf, sort_order, level, is_active, translations, created_at)
VALUES
    -- 8. iPhone (дочерняя для Смартфонов)
    (8,4, false, true, 1, 2, true,
     '{"en": {"name": "iPhone", "description": "Apple smartphones"},
       "ru": {"name": "iPhone", "description": "Смартфоны Apple"}}',
     NOW()),

    -- 9. Android (дочерняя для Смартфонов)
    (9,4, false, true, 2, 2, true,
     '{"en": {"name": "Android", "description": "Phones with Android OS"},
       "ru": {"name": "Android", "description": "Телефоны на Android"}}',
     NOW()),

    -- 10. Игровые ноутбуки (дочерняя для Ноутбуков)
    (10,5, false, true, 1, 2, true,
     '{"en": {"name": "Gaming Laptops", "description": "High-performance laptops"},
       "ru": {"name": "Игровые ноутбуки", "description": "Мощные ноутбуки"}}',
     NOW()),

    -- 11. Фэнтези (дочерняя для Художественной литературы)
    (11 ,7, false, true, 1, 2, true,
     '{"en": {"name": "Fantasy", "description": "Dragons and magic"},
       "ru": {"name": "Фэнтези", "description": "Драконы и магия"}}',
     NOW());

-- Обновляем is_leaf для родительских категорий, у которых появились дети
UPDATE category
SET is_leaf = false
WHERE id IN (1, 2, 3, 4, 5, 6, 7); -- Корневые и подкатегории 1-го уровня