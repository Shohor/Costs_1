SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE groups;
TRUNCATE user;
TRUNCATE roles;
TRUNCATE cost;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user (firsName, sirname, email, age, password, registred) VALUES
  ('llya', 'Shokhor', 'shohor@mail.ru', 34, 'Shish1983', '2017-01-01 10:00:00'),
  ('Natalia', 'Kozlovskaya', 'natashik84@list.ru', 33, 'Russia1984', '2017-01-01 10:00:00');

INSERT INTO groups (group_cost) VALUES
  ('Food'),
  ('Children'),
  ('House'),
  ('Presents');

INSERT INTO roles (user_id, role) VALUES
  (1,'ROLE_ADMIN'),
  (2,'ROLE_USER'),
  (1,'ROLE_USER');

INSERT INTO cost (price, date, user_id, group_id) VALUES
  (23.25, '2017-01-01 10:00:00', 2, 1),
  (55.45, '2017-01-01 10:00:00', 2, 3),
  (47.78, '2017-01-02 10:00:00', 2, 2),
  (8.21, '2017-01-02 10:00:00', 1, 1),
  (24.56, '2017-01-03 10:00:00', 1, 1),
  (11.66, '2017-01-04 10:00:00', 2, 4),
  (33.12, '2017-01-04 10:00:00', 1, 2)
