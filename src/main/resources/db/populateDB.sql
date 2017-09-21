SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE type_cost;
TRUNCATE user;
TRUNCATE roles;
TRUNCATE cost;
TRUNCATE type_income;
TRUNCATE cash_accounts_and_cards;
TRUNCATE type_cash_accounts_and_cards;
TRUNCATE income;
/*TRUNCATE periodicity;*/
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user (firstName, sirname, email, age, password, registred, enabled) VALUES
  ('Ilya', 'Shokhor', 'shohor@mail.ru', 34, '$2a$10$nLAIv6MfFCp2g1Y9iW.z0ubvrMzI2L0Sd.zimVj/REx2kQe1/A2xK', '2017-01-01 10:00:00',TRUE ),
  ('Natalia', 'Kozlovskaya', 'natashik84@list.ru', 33, '$2a$10$Q73bLAGTC8935gUsJI/RTeens/s9TokTGTWl6Ih3q3xQaGaVMHvUi', '2017-01-01 10:00:00',TRUE);

INSERT INTO type_cost (user_id, type, description) VALUES
  (1,'Food',""),
  (1,'Children',""),
  (2,'House',""),
  (1,'Presents',""),
  (2,'Food',"");

INSERT INTO roles (user_id, role) VALUES
  (1,'ROLE_ADMIN'),
  (2,'ROLE_USER'),
  (1,'ROLE_USER');

/*INSERT INTO periodicity (periodicity) VALUES
  ('EveryDay'),
  ('EveryWeek'),
  ('EveryMonth');*/

INSERT INTO type_income (type, description, user_id) VALUES
  ('Salary','',1),
  ('Salary','',2),
  ('Present','',2);

INSERT INTO cash_accounts_and_cards (description, user_id) VALUES
  ("",1),
  ("",1),
  ("",2);

INSERT INTO type_cash_accounts_and_cards (cash_accounts_and_cards_id, type) VALUES
  (1, 'Cash'),
  (2, 'Credit_Card'),
  (3, 'Debit_Card');

INSERT INTO cost (amount, date, user_id, group_id, description, cash_accounts_and_cards_id) VALUES
  (23.25, '2017-01-01 10:00:00', 2, 5,"",3),
  (55.45, '2017-01-01 10:00:00', 2, 3,"",3),
  (47.78, '2017-01-02 10:00:00', 2, 5,"",3),
  (8.21, '2017-01-02 10:00:00', 2, 5,"",3),
  (24.56, '2017-01-03 10:00:00', 2, 5,"",3),
  (11.66, '2017-01-04 10:00:00', 1, 4,"",1),
  (33.12, '2017-01-04 10:00:00', 1, 2,"",2);

INSERT INTO income (amount, description, date, cash_accounts_and_cards_id, user_id,type_income_id) VALUES
  (250,'','2017-01-01 10:00:00',2,1,1),
  (550,'','2017-01-02 10:00:00',2,1,1),
  (100,'','2017-01-01 10:00:00',1,1,2);