package eventsg.backend.dao;

import eventsg.backend.mapper.ReviewRowMapper;
import eventsg.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresReview")
public class ReviewDataAccessService implements ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addReview(UUID reviewId, Review review) {
        final String sql = "" +
                "INSERT INTO review (" +
                "reviewId, " +
                "reviewerId, " +
                "eventId, " +
                "rating, " +
                "content) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                reviewId,
                review.getReviewerId(),
                review.getEventId(),
                review.getRating(),
                review.getContent()
        );
    }

    @Override
    public Optional<Review> getReviewById(UUID selectedReviewId) {
        final String sql = "SELECT * FROM review WHERE reviewId = ?";
        Review review = jdbcTemplate.queryForObject(
                sql,
                new Object[]{selectedReviewId},
                new ReviewRowMapper());
        return Optional.ofNullable(review);
    }

    @Override
    public List<Review> getReviewsByEventId(UUID selectedEventId) {
        final String sql = "SELECT * FROM review WHERE eventId = ?";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new Object[]{selectedEventId},
                new ReviewRowMapper());
        return reviews;
    }

    @Override
    public List<Review> getAllReviews() {
        final String sql = "SELECT * FROM review";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new ReviewRowMapper());
        return reviews;
    }
}
