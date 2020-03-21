CREATE TABLE event (
    id UUID not null primary key,
    title VARCHAR(256) not null,
    description VARCHAR(256) not null,
    maxCapacity int not null,
    numOfParticipants int,
    overallRating float default (0.0),
    attendanceRate float default (0.0)
);

CREATE TABLE person (
    id UUID not null primary key,
    firstName VARCHAR(20) not null,
    lastName VARCHAR(20) not null
);

CREATE TABLE venue (
    venueId UUID not null primary key,
    address VARCHAR(100) not null,
    postalCode int not null,
    ownerId UUID not null,
    rentalFee float not null,
    area float not null,
    description VARCHAR(600),
    location VARCHAR(20) not null
);

CREATE TABLE review (
    reviewId UUID not null primary key,
    reviewerId UUID not null,
    subjectId UUID not null,
    rating int not null,
    content VARCHAR(600)
);