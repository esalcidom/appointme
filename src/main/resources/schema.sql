CREATE TABLE appointment(
    id VARCHAR(50) PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    date_time DATETIME NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NOT NULL
);

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password TEXT NOT NULL,
    algorithm VARCHAR(45) NOT NULL,
    enable BOOLEAN NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NOT NULL
);

CREATE TABLE authorities(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  authority VARCHAR(45) NOT NULL
);

CREATE TABLE user_authority(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    authority_id INT NOT NULL
);