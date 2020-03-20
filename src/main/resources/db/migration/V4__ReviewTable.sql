CREATE TABLE review (
    reviewId UUID not null primary key,
    reviewerId UUID not null,
    subjectId UUID not null,
    rating int not null,
    content VARCHAR(600)
)