INSERT INTO category (id, parent_id, sort_order, level, image, is_active, translations, created_at, updated_at)
VALUES (
           1,
           NULL,
           0,
           0,
           'image.png',
           true,
           '{"en": {"name": "Category 1", "description": "Description 1"}}', -- translations (JSONB)
           NOW(),
           NOW()
       );