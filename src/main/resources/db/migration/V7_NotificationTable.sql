CREATE TABLE notification (
          eventId UUID not null,
          foreign key (personId) references PersonTable(id),
          foreign key (eventId) references EventTable(id)
)