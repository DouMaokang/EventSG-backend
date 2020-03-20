CREATE TABLE user_interested_category (
    userId UUID not null,
    category VARCHAR(50) not null,
    PRIMARY KEY (userId, category)
);
