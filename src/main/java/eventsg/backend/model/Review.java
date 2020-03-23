package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Review {

    private UUID reviewId;
    private UUID reviewerId;
    private UUID eventId;
    private int rating;
    private String content;

    public Review(@JsonProperty("reviewId") UUID reviewId, @JsonProperty("reviewerId") UUID reviewerId,
                  @JsonProperty("eventId") UUID eventId, @JsonProperty("rating") int rating,
                  @JsonProperty("content") String content) {
        this.reviewId = reviewId;
        this.reviewerId = reviewerId;
        this.eventId = eventId;
        this.rating = rating;
        this.content = content;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public UUID getReviewerId() {
        return reviewerId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }
}
