CREATE TABLE user_saved_event (
    userId UUID not null,
    eventID UUID not null,
    primary key(userId, eventID)
);
