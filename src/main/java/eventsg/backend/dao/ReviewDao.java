package eventsg.backend.dao;

import eventsg.backend.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewDao {

    /**
     * Insert a new Review into the review table
     * @param review Review object to be inserted
     * @return 1 if insertion is successful; else 0;
     */
    int addReview(Review review);

    /**
     * Get the Review record matching the input reviewId
     * @param reviewId the reviewId to be searched
     * @return a Review object if found such a review with the required reviewId; else return null
     */
    Optional<Review> getReviewById(UUID reviewId);

    // not needed List<Review> getAllReviews(); update and delete are not required as well

    /**
     * Check if the user has reviewed the event or not.
     * @param eventId UUID of the event.
     * @param reviewerId UUID of the user.
     * @return True if the user has reviewed the event.
     */
    boolean checkIfReviewed(UUID eventId, UUID reviewerId);

    /**
     * Get all the review records related to the input eventId as a list of Review objects
     * @param selectedEventId eventId to be selected
     * @return a list of Review objects that has the specified eventId
     */
    List<Review> getReviewsByEventId(UUID selectedEventId);

    /**
     * Get all the review records from the database
     * @return a list of Review objects, which are current records in the review database
     */
    List<Review> getAllReviews();

    /**
     * Delete the review by given UUID.
     * @param reviewId UUID of the review.
     * @return 0 or 1.
     */
    int deleteReviewById(UUID reviewId);

    /**
     * Update review with the provided review.
     * @param reviewId UUID of the review to be updated.
     * @param review review to replace the old review.
     * @return 0 or 1.
     */
    int updateReviewById(UUID reviewId, Review review);


}