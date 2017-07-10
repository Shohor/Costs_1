DROP TABLE IF EXISTS cost;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  firsName VARCHAR(45) NOT NULL,
  sirname VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  age INT,
  password VARCHAR(45) NOT NULL,
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

CREATE TABLE groups
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  group_cost VARCHAR(45) NOT NULL
);
CREATE UNIQUE INDEX group_unique_idx ON groups(group_cost);

CREATE TABLE cost
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  price DOUBLE NOT NULL,
  date DATE NOT NULL,
  user_id INT NOT NULL,
  group_id INT NOT NULL,
  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE

);