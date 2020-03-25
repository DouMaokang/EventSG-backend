CREATE TABLE registration (
       personId UUID not null,
       eventId UUID not null
--        foreign key (personId) references PersonTable(id),
--        foreign key (eventId) references EventTable(id)
)