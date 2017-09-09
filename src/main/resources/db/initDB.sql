DROP TABLE IF EXISTS cost;
DROP TABLE IF EXISTS type_cost;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS income;
DROP TABLE IF EXISTS type_cash_accounts_and_cards;
DROP TABLE IF EXISTS periodicity;
DROP TABLE IF EXISTS cash_accounts_and_cards;
DROP TABLE IF EXISTS type_income;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(45) NOT NULL,
  sirname VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  age INT,
  enabled BOOLEAN,
  password VARCHAR(255) NOT NULL,
  registred DATE NOT NULL
);
CREATE UNIQUE INDEX user_unique_email_idx ON user(email);

CREATE TABLE roles
(
  user_id INT NOT NULL,
  role VARCHAR(45) NOT NULL,
  CONSTRAINT user_role_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE type_cost
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  type VARCHAR(45) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE

);

CREATE TABLE cash_accounts_and_cards
(
  id INT PRIMARY KEY UNIQUE KEY AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  description VARCHAR(255),
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE cost
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  date DATE NOT NULL,
  description VARCHAR(255),
  user_id INT NOT NULL,
  group_id INT NOT NULL,
  cash_accounts_and_cards_id INT NOT NULL,
  FOREIGN KEY (group_id) REFERENCES type_cost(id) ON DELETE CASCADE,
  FOREIGN KEY (cash_accounts_and_cards_id) REFERENCES cash_accounts_and_cards(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE periodicity
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  periodicity VARCHAR(55)
);

CREATE TABLE type_income
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(55) NOT NULL,
  description VARCHAR(255),
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE income
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  date DATE NOT NULL,
  description VARCHAR(255),
  type_income_id INT NOT NULL,
  cash_accounts_and_cards_id INT NOT NULL,
  periodicity_id INT,
  user_id INT NOT NULL,
  FOREIGN KEY (type_income_id) REFERENCES type_income(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
  FOREIGN KEY (periodicity_id) REFERENCES periodicity(id),
  FOREIGN KEY (cash_accounts_and_cards_id) REFERENCES cash_accounts_and_cards(id) ON DELETE CASCADE
);

CREATE TABLE type_cash_accounts_and_cards
(
  cash_accounts_and_cards_id INT PRIMARY KEY,
  type VARCHAR(55),
  CONSTRAINT type_idx UNIQUE (cash_accounts_and_cards_id, type),
  FOREIGN KEY (cash_accounts_and_cards_id) REFERENCES cash_accounts_and_cards(id) ON DELETE CASCADE
);