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

    @PostMapping
    public void addReview(@Valid @NotNull @RequestBody Review review) {
        reviewService.addReview(review);
    }

    @GetMapping(path = "reviewId/{selectedReviewId}")
    public Optional<Review> getReviewById(@PathVariable UUID selectedReviewId) {
        return reviewService.getReviewById(selectedReviewId);
    }

    @GetMapping(path = "eventId/{selectedEventId}")
    public List<Review> getReviewsByEventId(@PathVariable UUID selectedEventId) {
        return reviewService.getReviewsByEventId(selectedEventId);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}