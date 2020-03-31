package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private UUID notificationId;
    /** Type can be "registration", "event" or "review". */
    private String type;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeCreated;
    private UUID eventId;
    private UUID userId;


    public Notification(String type, LocalDateTime timeCreated, UUID eventId, UUID userId) {
        this.type = type;
        this.timeCreated = timeCreated;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Notification(UUID notificationId, String type, LocalDateTime timeCreated, UUID eventId, UUID userId) {
        this.notificationId = notificationId;
        this.type = type;
        this.timeCreated = timeCreated;
        this.eventId = eventId;
        this.userId = userId;

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
