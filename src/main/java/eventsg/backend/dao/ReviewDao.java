package eventsg.backend.dao;

import eventsg.backend.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewDao {

    int addReview(UUID reviewId, Review review);

    default int addReview(Review review) {
        UUID reviewId = UUID.randomUUID();
        return addReview(reviewId, review);
    }

    Optional<Review> getReviewById(UUID reviewId);

    // not needed List<Review> getAllReviews(); update and delete are not required as well

    List<Review> getReviewsByEventId(UUID selectedEventId);

    List<Review> getAllReviews();

}
