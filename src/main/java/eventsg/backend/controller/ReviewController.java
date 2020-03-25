package eventsg.backend.controller;

import eventsg.backend.model.Review;
import eventsg.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/review")
@RestController
public class ReviewController {

    public final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Answering an Http POST request
     * Add a new Review to the database
     * @param review Review to be added
     */
    @PostMapping
    public void addReview(@Valid @NotNull @RequestBody Review review) {
        reviewService.addReview(review);
    }

    /**
     * Answering an Http GET request
     * Check and return a Review object matching the input reviewId
     * @param selectedReviewId the review with this reviewId would be retrieved
     * @return return a Review object matching the input reviewId
     */
    @GetMapping(path = "reviewId/{selectedReviewId}")
    public Optional<Review> getReviewById(@PathVariable UUID selectedReviewId) {
        return reviewService.getReviewById(selectedReviewId);
    }

    /**
     * Answering an Http GET request
     * Check and return a list of Review objects with the input eventId
     * @param selectedEventId the eventId to be included in the search criteria
     * @return return a list of Review objects
     */
    @GetMapping(path = "eventId/{selectedEventId}")
    public List<Review> getReviewsByEventId(@PathVariable UUID selectedEventId) {
        return reviewService.getReviewsByEventId(selectedEventId);
    }

    /**
     * Answeing an Http GET request
     * @return Return a list of all Review objects currently in the database
     */
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}