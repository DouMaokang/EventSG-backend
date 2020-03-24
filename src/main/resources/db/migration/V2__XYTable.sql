CREATE TABLE event (
    id UUID not null primary key,
    title VARCHAR(256) not null,
    description VARCHAR(256) not null,
    maxCapacity int not null,
    numOfParticipants int,
    overallRating float default (0.0),
    attendanceRate float default (0.0)
);

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
);

CREATE TABLE user_interested_category (
    userId UUID not null,
    category VARCHAR(50) not null,
    PRIMARY KEY (userId, category)
);

CREATE TABLE user_saved_event (
    userId UUID not null,
    eventID UUID not null,
    primary key(userId, eventID)
);