DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS users;

CREATE TABLE book (
   id INT NOT NULL,
   title VARCHAR(50) NOT NULL,
   author VARCHAR(20) NOT NULL
);

CREATE TABLE users (
    user_id INT NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    role VARCHAR(20) NOT NULL
);