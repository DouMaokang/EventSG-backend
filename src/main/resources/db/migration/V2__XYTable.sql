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
    organization VARCHAR(50) NOT NULL,
    image VARCHAR(256)  DEFAULT 'assets/female-1.jpg'
);

CREATE TABLE user_interested_category (
    userId UUID not null,
    category VARCHAR(50) not null,
    PRIMARY KEY (userId, category)
);

CREATE TABLE user_saved_event (
    userId UUID not null,
    eventId UUID not null,
    primary key(userId, eventId)
);

CREATE TABLE registration (
       userId UUID not null,
       eventId UUID not null
);