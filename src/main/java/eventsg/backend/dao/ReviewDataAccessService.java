package eventsg.backend.dao;

import eventsg.backend.mapper.ReviewRowMapper;
import eventsg.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("reviewDao")
public class ReviewDataAccessService implements ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a new Review into the review table
     * @param review Review object to be inserted
     * @return 1 if insertion is successful; else 0;
     */
    @Override
    public int addReview(Review review) {
        final String sql = "" +
                "INSERT INTO review (" +
                "reviewId, " +
                "reviewerId, " +
                "eventId, " +
                "rating, " +
                "content) " +
                "VALUES (?, ?, ?, ?, ?)";
        UUID reviewId = UUID.randomUUID();
        return jdbcTemplate.update(
                sql,
                reviewId,
                review.getReviewerId(),
                review.getEventId(),
                review.getRating(),
                review.getContent()
        );
    }

    /**
     * Get the Review record matching the input reviewId
     * @param selectedReviewId the reviewId to be searched
     * @return a Review object if found such a review with the required reviewId; else return null
     */
    @Override
    public Optional<Review> getReviewById(UUID selectedReviewId) {
        final String sql = "SELECT * FROM review WHERE reviewId = ?";
        Review review = jdbcTemplate.queryForObject(
                sql,
                new Object[]{selectedReviewId},
                new ReviewRowMapper());
        return Optional.ofNullable(review);
    }

    /**
     * Check if the user has reviewed the event or not.
     * @param selectedEventId UUID of the event.
     * @param selectedReviewerId UUID of the user.
     * @return True if the user has reviewed the event.
     */
    public boolean checkIfReviewed(UUID selectedEventId, UUID selectedReviewerId) {
        final String sql = "SELECT * FROM review WHERE eventId = ? AND reviewerId = ?";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new Object[]{selectedEventId, selectedReviewerId},
                new ReviewRowMapper());
        return reviews.size() != 0;
    }

    /**
     * Get all the review records related to the input eventId as a list of Review objects
     * @param selectedEventId eventId to be selected
     * @return a list of Review objects that has the specified eventId
     */
    @Override
    public List<Review> getReviewsByEventId(UUID selectedEventId) {
        final String sql = "SELECT * FROM review WHERE eventId = ?";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new Object[]{selectedEventId},
                new ReviewRowMapper());
        return reviews;
    }

    /**
     * Get all the review records from the database
     * @return a list of Review objects, which are current records in the review database
     */
    @Override
    public List<Review> getAllReviews() {
        final String sql = "SELECT * FROM review";
        List<Review> reviews = jdbcTemplate.query(
                sql,
                new ReviewRowMapper());
        return reviews;
    }

    /**
     * Delete the review by given UUID.
     * @param reviewId UUID of the review.
     * @return 0 or 1.
     */
    @Override
    public int deleteReviewById(UUID reviewId) {
        final String sql = "" + "DELETE FROM review WHERE reviewId = ?";
        return jdbcTemplate.update(sql, reviewId);
    }

    /**
     * Update review with the provided review.
     * @param reviewId UUID of the review to be updated.
     * @param review review to replace the old review.
     * @return 0 or 1.
     */
    @Override
    public int updateReviewById(UUID reviewId, Review review) {
        String sql = "" +
                "UPDATE review " +
                "SET " +
                "reviewId = ?, " +
                "reviewerId = ?, " +
                "eventId = ?, " +
                "rating = ?, " +
                "content = ? " +
                "WHERE reviewId = ?";
        return jdbcTemplate.update(
                sql,
                reviewId,
                review.getReviewerId(),
                review.getEventId(),
                review.getRating(),
                review.getContent(),
                reviewId
        );
    }


}