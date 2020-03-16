CREATE TABLE event (
    id UUID not null primary key,
    title VARCHAR(256) not null,
    description VARCHAR(256) not null,
    maxCapacity int not null,
    numOfParticipants int,
    overallRating float default (0.0),
    attendanceRate float default (0.0)
)