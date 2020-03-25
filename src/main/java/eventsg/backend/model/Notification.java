package eventsg.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification implements Comparable<Notification> {

    //maybe can have event object for more information
    private UUID eventId;

    private LocalDateTime eventTime;

    //range from 0 to 7
    private int days;

    //whether need to be notified today (only when first added or 0,1,3,7 days before the event)
    private Boolean notify;

    //attribute read or not should be done in frontend

    public Notification(UUID eventId, LocalDateTime eventTime, int days, Boolean notify) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.days = days;
        this.notify = notify;
    }

    @Override
    public int compareTo(Notification o) {
        return this.eventTime.isBefore(o.getEventTime())?1:0;
    }

    public int getDays() {
        return days;
    }

    public UUID getEventId() {
        return eventId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public Boolean getNotify() {
        return notify;
    }

}
