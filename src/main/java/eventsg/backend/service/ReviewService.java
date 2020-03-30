package eventsg.backend.service;

import eventsg.backend.dao.ReviewDao;
import eventsg.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewService(@Qualifier("reviewDao") ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public int addReview(Review review) {
        return reviewDao.addReview(review);
    }

    public Optional<Review> getReviewById(UUID selectedReviewId) {
        return reviewDao.getReviewById(selectedReviewId);
    }

    public boolean checkIfReviewed(UUID selectedEventId, UUID selectedReviewerId) {
        return reviewDao.checkIfReviewed(selectedEventId, selectedReviewerId);
    }
    public List<Review> getReviewsByEventId(UUID selectedEventId) {
        return reviewDao.getReviewsByEventId(selectedEventId);
    }

    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    public int deleteReviewById(UUID reviewId) {
        return reviewDao.deleteReviewById(reviewId);
    }

    public int updateReviewById(UUID reviewId, Review review) {
        return reviewDao.updateReviewById(reviewId, review);
    }

}