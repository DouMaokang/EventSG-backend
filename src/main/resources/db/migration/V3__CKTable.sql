CREATE TABLE notification (
       notificationId UUID NOT NULL PRIMARY KEY,
       eventId UUID NOT NULL FOREIGN KEY REFERENCES event(eventId),
       type VARCHAR(32) NOT NULL,
       reviewId UUID FOREIGN KEY  REFERENCES review(reviewId),
       capacityId INT,
       time TIMESTAMP NOT NULL,
);
