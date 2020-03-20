CREATE TABLE users (
    userId UUID NOT NULL PRIMARY KEY,
    userName VARCHAR(50) UNIQUE,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password varchar(50) NOT NULL,
    birthday DATE NOT NULL,
    phoneNum INT UNIQUE,
    occupation VARCHAR(50) NOT NULL,
    organization VARCHAR(50) NOT NULL
)
