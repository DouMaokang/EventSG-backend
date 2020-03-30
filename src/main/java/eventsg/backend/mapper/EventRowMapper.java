package eventsg.backend.mapper;

import eventsg.backend.dao.ReviewDao;
import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
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
        UUID organizerId = UUID.fromString(resultSet.getString("organizerId"));

        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
        LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
        LocalDateTime registrationDeadline =
                resultSet.getTimestamp("registrationDeadline").toLocalDateTime();
        Integer capacity = resultSet.getInt("capacity");
        Integer numOfParticipants = resultSet.getInt("numOfParticipants");
        float avgRating = resultSet.getFloat("avgRating");
//        List<Review> reviewList = null;
        String category = resultSet.getString("category");
        String status = resultSet.getString("status");
        UUID venueId = UUID.fromString(resultSet.getString("venueId"));


        Event event = new Event(id, organizerId, title, description, startTime, endTime,
                registrationDeadline, capacity, numOfParticipants, avgRating,
                category, venueId, status);
        return event;
    }

}
