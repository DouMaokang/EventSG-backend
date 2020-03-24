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

    /**
     * get reviewId of the instance
     * @return reviewId
     */
    public UUID getReviewId() {
        return reviewId;
    }

    /**
     * get reviewerId of the instance
     * @return reviewerId
     */
    public UUID getReviewerId() {
        return reviewerId;
    }

    /**
     * get eventId of the instance
     * @return eventId
     */
    public UUID getEventId() {
        return eventId;
    }

    /**
     * get rating of the instance
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * get content of the instance
     * @return content
     */
    public String getContent() {
        return content;
    }
}