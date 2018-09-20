INSERT INTO shops (name) VALUES ('Phone Shop'), ('Watch Shop'), ('Textbook Shop');

INSERT INTO products (name, price, shop_id) VALUES ('iPhone X', 999.99, 1), ('iPhone 8 Plus', 599.99, 1),
('Samsung S9+', 839.99, 1), ('Huawei P20 Pro', 748.99, 1);

INSERT INTO products (name, price, shop_id) VALUES ('Apple Watch Series 4', 519.99, 2), ('Cardinal Wenger Swiss', 374.96, 2),
('Galaxy Watch', 419.99, 2), ('Daniel Wellington Classic', 215.00, 2);

INSERT INTO products (name, price, shop_id) VALUES ('Calculus 2', 152.95, 3), ('Organizational Behaviour', 182.99, 3),
('Corporate Finance', 163.00, 3), ('Object-Oriented Programming', 144.50, 3);

INSERT INTO orders (total_price, shop_id) VALUES (8879.90, 1), (10791.84, 1),
(5584.70, 2), (7949.81, 2), (0, 3), (7789.33, 3);

INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (2, 999.99, 1, 1), (1, 999.99, 1, 1);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (5, 599.99, 2, 2), (3, 599.99, 2, 2);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (3, 839.99, 1, 3), (4, 839.99, 1, 3);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (7, 748.99, 2, 4), (1, 748.99, 2, 4);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (3, 519.99, 3, 5), (3, 519.99, 3, 5);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (6, 374.96, 3, 6), (2, 374.96, 4, 6);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (7, 419.99, 4, 7), (4, 419.99, 4, 7);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (1, 215.0	, 3, 8), (12, 215.0, 4, 8);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (5, 152.95, 6, 9), (5, 152.95, 6, 9);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (9, 182.99, 6, 10), (8, 182.99, 6, 10);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (6, 163.0, 6, 11), (8, 163.0, 6, 11);
INSERT INTO line_items (quantity, price, order_id, product_id) VALUES (4, 144.5, 6, 12), (2, 144.5, 6, 12);