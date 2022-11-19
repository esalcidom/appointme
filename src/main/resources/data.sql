INSERT INTO users (id, username, password, algorithm, enable, created, updated) VALUES (1, 'myAdmin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT', true, '2022-12-31 23.59.59', '2022-12-31 23.59.59');
INSERT INTO users (id, username, password, algorithm, enable, created, updated) VALUES (1, 'myUser', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT', true, '2022-12-31 23.59.59', '2022-12-31 23.59.59');
INSERT INTO authorities (id, authority) VALUES (1, 'WRITE');
INSERT INTO authorities (id, authority) VALUES (2, 'READ');
INSERT INTO user_authority (id, user_id, authority_id) VALUES (1, 1, 1);
INSERT INTO user_authority (id, user_id, authority_id) VALUES (2, 1, 2);
INSERT INTO user_authority (id, user_id, authority_id) VALUES (3, 2, 2);