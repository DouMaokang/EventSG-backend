package eventsg.backend.controller;

import eventsg.backend.model.Review;
import eventsg.backend.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/review")
@RestController
public class ReviewController {

    public final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public void addReview(@Valid @NotNull @RequestBody Review review) {
        reviewService.addReview(review);
    }

    @GetMapping(path = "{reviewId}")
    public Optional<Review> getReviewById(@PathVariable UUID selectedReviewId) {
        return reviewService.getReviewById(selectedReviewId);
    }

    @GetMapping(path = "{subjectId}")
    public List<Review> getReviewsBySubjectId(@PathVariable UUID selectedSubjectId) {
        return reviewService.getReviewsBySubjectId(selectedSubjectId);
    }
}
