SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE costGroups;
TRUNCATE user;
TRUNCATE roles;
TRUNCATE cost;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user (firstName, sirname, email, age, password, registred) VALUES
  ('Ilya', 'Shokhor', 'shohor@mail.ru', 34, '$2a$10$nLAIv6MfFCp2g1Y9iW.z0ubvrMzI2L0Sd.zimVj/REx2kQe1/A2xK', '2017-01-01 10:00:00'),
  ('Natalia', 'Kozlovskaya', 'natashik84@list.ru', 33, '$2a$10$Q73bLAGTC8935gUsJI/RTeens/s9TokTGTWl6Ih3q3xQaGaVMHvUi', '2017-01-01 10:00:00');

INSERT INTO costGroups (user_id, group_cost) VALUES
  (1,'Food'),
  (1,'Children'),
  (2,'House'),
  (1,'Presents'),
  (2,'Food');

INSERT INTO roles (user_id, role) VALUES
  (1,'ROLE_ADMIN'),
  (2,'ROLE_USER'),
  (1,'ROLE_USER');

INSERT INTO cost (price, date, user_id, group_id, description) VALUES
  (23.25, '2017-01-01 10:00:00', 2, 5,""),
  (55.45, '2017-01-01 10:00:00', 2, 3,""),
  (47.78, '2017-01-02 10:00:00', 2, 5,""),
  (8.21, '2017-01-02 10:00:00', 2, 5,""),
  (24.56, '2017-01-03 10:00:00', 2, 5,""),
  (11.66, '2017-01-04 10:00:00', 1, 4,""),
  (33.12, '2017-01-04 10:00:00', 1, 2,"")
