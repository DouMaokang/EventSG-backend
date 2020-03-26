package eventsg.backend.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Notification {

    private UUID notificationId;

    //maybe can have event object for more information
    private UUID eventId;

    //type 1,2,3 for review, update and capacity notification respectively
    private String type;

    //only for review notification
    private UUID reviewId;

    //only for capacity notification, 1 for 80%, 2 for 100%
    private int capacityLevel;

    private LocalDateTime time;

    public Notification(UUID notificationId, UUID eventId, String type, UUID reviewId, int capacityLevel, LocalDateTime time) {
        this.notificationId = notificationId;
        this.eventId = eventId;
        this.type = type;
        this.reviewId = reviewId;
        this.capacityLevel = capacityLevel;
        this.time = time;
    }

    //review notification
    public Notification(UUID eventId, UUID reviewId, LocalDateTime time) {
        this.notificationId = UUID.randomUUID();
        this.eventId = eventId;
        this.type = "review";
        this.reviewId = reviewId;
        this.capacityLevel = 0;
        this.time = time;
    }

    //update notification
    public Notification(UUID eventId, LocalDateTime time) {
        this.notificationId = UUID.randomUUID();
        this.eventId = eventId;
        this.type = "update";
        this.reviewId = null;
        this.capacityLevel = 0;
        this.time = time;
    }

    //capacity notification
    public Notification(UUID eventId, int capacityLevel, LocalDateTime time) {
        this.notificationId = UUID.randomUUID();
        this.eventId = eventId;
        this.type = "capacity";
        this.reviewId = null;
        this.capacityLevel = capacityLevel;
        this.time = time;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getType() {
        return type;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public int getCapacityLevel() {
        return capacityLevel;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
