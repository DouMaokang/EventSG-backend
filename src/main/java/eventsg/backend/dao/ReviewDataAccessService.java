package eventsg.backend.dao;

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
                "subjectId, " +
                "rating, " +
                "content) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                reviewId,
                review.getReviewerId(),
                review.getSubjectId(),
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
                (resultSet, i) -> {
                    UUID reviewId = UUID.fromString(resultSet.getString("reviewId"));
                    UUID reviewerId = UUID.fromString(resultSet.getString("reviewerId"));
                    UUID subjectId = UUID.fromString(resultSet.getString("subjectId"));
                    int rating = Integer.parseInt(resultSet.getString("rating"));
                    String content = resultSet.getString("content");
                    return new Review(reviewId, reviewerId, subjectId, rating, content);
                });
        return Optional.ofNullable(review);
    }

    @Override
    public List<Review> getReviewsBySubjectId(UUID selectedSubjectId) {
        final String sql = "SELECT * FROM review WHERE subjectId = ?";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new Object[]{selectedSubjectId},
                (resultSet, i) -> {
                    UUID reviewId = UUID.fromString(resultSet.getString("reviewId"));
                    UUID reviewerId = UUID.fromString(resultSet.getString("reviewerId"));
                    UUID subjectId = UUID.fromString(resultSet.getString("subjectId"));
                    int rating = Integer.parseInt(resultSet.getString("rating"));
                    String content = resultSet.getString("content");
                    return new Review(reviewId, reviewerId, subjectId, rating, content);
                });
        return reviews;
    }

    @Override
    public List<Review> getAllReviews() {
        final String sql = "SELECT * FROM review";
        List<Review> reviews = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID reviewId = UUID.fromString(resultSet.getString("reviewId"));
            UUID reviewerId = UUID.fromString(resultSet.getString("reviewerId"));
            UUID subjectId = UUID.fromString(resultSet.getString("subjectId"));
            int rating = Integer.parseInt(resultSet.getString("rating"));
            String content = resultSet.getString("content");
            return new Review(reviewId, reviewerId, subjectId, rating, content);
        });
        return reviews;
    }
}
