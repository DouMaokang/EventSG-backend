CREATE TABLE user (
    userId UUID not null primary key,
    userName VARCHAR(50) unique,
    firstName VARCHAR(50) not null,
    lastName VARCHAR(50 not null,
    email VARCHAR(50) unique,
    password varchar(50) not null,
    birthday DATE not null,
    phoneNum INTEGER unique,
    occupation VARCHAR(50) not null,
    organization VARCHAR(50) not null
);
