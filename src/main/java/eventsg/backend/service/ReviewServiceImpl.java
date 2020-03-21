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
public class ReviewServiceImpl {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(@Qualifier("postgresReview") ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public int addReview(Review review) {
        return reviewDao.addReview(review);
    }

    public Optional<Review> getReviewById(UUID selectedReviewId) {
        return reviewDao.getReviewById(selectedReviewId);
    }

    public List<Review> getReviewsBySubjectId(UUID selectedSubjectId) {
        return reviewDao.getReviewsBySubjectId(selectedSubjectId);
    }

    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

}
