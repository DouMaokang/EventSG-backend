package eventsg.backend.model;

import java.util.UUID;

/**
 * This class represents an event.
 */
public class Event {
    /** The unique event id assigned by the system when the event is created. */
    private UUID eventId;
    /** The title of the event. */
    private String title;
    /** The description of an event. */
    private String description;

    /* TODO:
        Finalize the data type pf startTime, endTime and registrationDeadline.
        Take app system time into consideration
     */
    // private Time startTime; // What type should we use?
    // private Time endTime;
    // private Time registrationDeadline;

    /** The maximum number of people the event can hold. */
    private int maxCapacity;
    /** The number of people who signed up for the event. */
    private int numOfParticipants;
    /** The average of all rating given to the event. */
    private float overallRating;

    /** The attendance rate of the event. */
    private float attendanceRate;
    // reviewsAndRatings ??
    // private String category; // String?
    // private String status; // ?
    // valueId ??

    /**
     * To construct an event.
     *
     * @param id the system assigned id of the event
     * @param title the title of the event
     * @param description the description of the event
     * @param maxCapacity the maximum number of participants allowed
     * @param numOfParticipants the number of registered people
     * @param overallRating the average rating of the event
     * @param attendanceRate the attendance rate of the event
     */
    public Event(final UUID id, String title, String description, int maxCapacity, int numOfParticipants, float overallRating, float attendanceRate) {
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
    // TODO: Complete calculateDuration() method
    public Object calculateDuration() {
        return null;
    }

    /**
     * Gets the event id.
     * @return event id
     */
    public UUID getEventId() {
        return eventId;
    }

    /**
     * Gets the title of the event.
     * @return event title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     * @param title the title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the event.
     * @return event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     * @param description the description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the maximum capacity of the event.
     * @return maximum capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets the maximum capacity of the event.
     * @param maxCapacity the maximum capacity of the event
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Gets the number of participants.
     * @return the number of participants
     */
    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    /**
     * Sets the number of participants.
     * @param numOfParticipants the number of participants
     */
    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    // TODO: Implement calculateOverallRating(List<Review> reviewList)

    /**
     * Gets the average rating of the event.
     * @return average rating
     */
    public float getOverallRating() {
        return overallRating;
    }

    // TODO: Implement calculateAttendanceRating(int numOfPeopleCheckedIn)

    /**
     * Gets the attendance rate of the event.
     * @return the attendance rate
     */
    public float getAttendanceRate() {
        return attendanceRate;
    }

}
