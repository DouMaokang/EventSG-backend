package eventsg.backend.model;

import java.util.UUID;

/**
 * This class represents an event.
 */
public class Event {
    private UUID eventId;
    private String title;
    private String description;
//    private Time startTime; // What type should we use?
//    private Time endTime;
//    private Time registrationDeadline;
    private int maxCapacity;
    private int numOfParticipants;
    private float overallRating;

    /**
     * The attendance rate of the event
     */
    private float attendanceRate;
    // reviewsAndRatings ??
    // private String category; // String?
    // private String status; // ?
    // valueId ??

    protected Event() {}

    public Event(UUID id, String title, String description, int maxCapacity, int numOfParticipants, float overallRating, float attendanceRate) {
        this.eventId = id;
        this.title = title;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.numOfParticipants = numOfParticipants;
        this.overallRating = overallRating;
        this.attendanceRate = attendanceRate;
    }

    /**
     * Calculates the vacancy of an event.
     * @return the number of vacancies of the event.
     */
    public int calculateVacancy() {
        return this.maxCapacity - this.numOfParticipants;
    }

    /**
     * Calculates the duration of an event.
     * @return the duration of the event.
     */
    public Object calculateDuration() {
        return null;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    public float getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(float attendanceRate) {
        this.attendanceRate = attendanceRate;
    }
}
