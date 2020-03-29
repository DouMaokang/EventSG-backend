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
     * @param reviewId the review with this reviewId would be retrieved
     * @return return a Review object matching the input reviewId
     */
    @GetMapping(path = "{reviewId}")
    public Optional<Review> getReviewById(@PathVariable UUID reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    /**
     * Answering an Http GET request
     * Check and return a list of Review objects with the input eventId
     * @param eventId the eventId to be included in the search criteria
     * @return return a list of Review objects
     */
    @GetMapping(path = "event/{eventId}")
    public List<Review> getReviewsByEventId(@PathVariable UUID eventId) {
        return reviewService.getReviewsByEventId(eventId);
    }

    /**
     * Answeing an Http GET request
     * @return Return a list of all Review objects currently in the database
     */
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    /**
     * A method answering to Http DELETE request
     * Deleting a record in the Review database which has the input reviewId
     * @param reviewId the record with this reviewId would be deleted
     */
    @DeleteMapping(path = "{reviewId}")
    public void deleteReviewById(@PathVariable("reviewId") UUID reviewId) {
        reviewService.deleteReviewById(reviewId);
    }

    /**
     * A method answering to Http PUT request with path as "{reviewId}"
     * Updating the record in the Review database
     * @param reviewId the record in the review database with this reviewId would be updated
     * @param review the review info used to update the existing record in the database
     */
    @PutMapping(path = "{reviewId}")
    public void updateReviewById(@PathVariable UUID reviewId, @Valid @NotNull @RequestBody Review review) {
        reviewService.updateReviewById(reviewId, review);
    }
}