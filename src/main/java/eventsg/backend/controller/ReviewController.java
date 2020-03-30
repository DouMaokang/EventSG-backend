package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import eventsg.backend.model.User;
import eventsg.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import eventsg.backend.service.EventService;
import eventsg.backend.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RequestMapping("api/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, EventService eventService, UserService userService) {
        this.reviewService = reviewService;
        this.eventService = eventService;
        this.userService = userService;
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
    public List<Map<String, Object>> getReviewsByEventId(@PathVariable UUID eventId) {
        List<Review> reviewList = reviewService.getReviewsByEventId(eventId);
        return generateResponseList(reviewList);
    }

    /**
     * Answeing an Http GET request
     * @return Return a list of all Review objects currently in the database
     */
    @GetMapping
    public List<Map<String, Object>> getAllReviews() {
        List<Review> reviewList = reviewService.getAllReviews();
        return generateResponseList(reviewList);
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


    private Map<String, Object> generateResponse(Review review) {
        UUID reviewerId = review.getReviewerId();
        User reviewer;
        try {
            reviewer = userService.getUserById(reviewerId).orElse(null);
        } catch (Exception e) {
            reviewer = null;
        }
        Event event;
        try {
            event = eventService.getEventById(review.getEventId());
        } catch (Exception e) {
            event = null;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("review", review);
        response.put("reviewer", reviewer);
        response.put("event", event);
        return  response;
    }

    private List<Map<String, Object>> generateResponseList (List<Review> reviewList) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Review review : reviewList) {
            responseList.add(generateResponse(review));
        }
        return responseList;
    }
}