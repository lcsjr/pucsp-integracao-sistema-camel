DROP TABLE IF EXISTS pessoa;

CREATE TABLE pessoa (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);