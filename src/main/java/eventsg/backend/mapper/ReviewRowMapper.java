package eventsg.backend.mapper;

import eventsg.backend.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int numRow) throws SQLException {
        UUID reviewId = UUID.fromString(resultSet.getString("reviewId"));
        UUID reviewerId = UUID.fromString(resultSet.getString("reviewerId"));
        UUID eventId = UUID.fromString(resultSet.getString("eventId"));
        int rating = Integer.parseInt(resultSet.getString("rating"));
        String content = resultSet.getString("content");
        return new Review(reviewId, reviewerId, eventId, rating, content);
    }
}
