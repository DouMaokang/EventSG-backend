package eventsg.backend.mapper;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("eventId"));
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
        LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();;
        LocalDateTime registrationDeadline =
                resultSet.getTimestamp("registrationDeadline").toLocalDateTime();;
        Integer capacity = resultSet.getInt("capacity");
        Integer numOfParticipants = resultSet.getInt("numOfParticipants");
        float avgRating = resultSet.getFloat("avgRating");
        String status = resultSet.getString("status");

        // TODO: Implement getAllReviews()
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("My review"));
        String category = resultSet.getString("category");

        Event event = new Event(id, title, description, startTime, endTime,
                registrationDeadline, capacity, numOfParticipants, avgRating,
                reviews, category, status);
        return event;
    }

}
