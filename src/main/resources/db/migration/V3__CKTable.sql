CREATE TABLE notification (
        userId UUID NOT NULL,
        notificationId UUID NOT NULL PRIMARY KEY,
        type VARCHAR(32) NOT NULL,
        timeCreated TIMESTAMP NOT NULL,
        eventId UUID NOT NULL
);
