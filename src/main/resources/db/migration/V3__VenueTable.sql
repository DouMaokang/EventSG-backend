CREATE TABLE venue (
    venueId UUID not null primary key,
    address VARCHAR(100) not null,
    postalCode int not null,
    ownerId UUID not null,
    rentalFee double not null,
    area double not null,
    description VARCHAR(600),
    location VARCHAR(20) not null
)