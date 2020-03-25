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
    eventId UUID not null,
    rating float not null,
    content VARCHAR(600)
);