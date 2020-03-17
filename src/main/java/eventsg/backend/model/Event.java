package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
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

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endTime;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime registrationDeadline;

    /** The number of people the event can hold. */
    private Integer capacity;

    /** The number of people who signed up for the event. */
    private Integer numOfParticipants;

    /** The average of all rating given to the event. */
    private float avgRating;

    private List<Review> reviewList;

    private String category;

    /**
     * The status of the event, including saved, posted, cancelled and completed.
     */
    private String status;

    public Event() {

    }

    /**
     *
     * @param title the title of the event (<= 64 characters)
     * @param description the text description of the event (<= ? characters)
     * @param startTime the start time of the event (yyyy-MM-dd hh:mm:ss)
     * @param endTime the end time of the event (yyyy-MM-dd hh:mm:ss)
     * @param registrationDeadline the deadline for event registration (yyyy-MM-dd hh:mm:ss)
     * @param capacity the number of people the event can hold
     * @param numOfParticipants the number of people who signed up for the event (<= capacity)
     * @param avgRating the average rating of the event
     * @param reviewList the list of reviews given to the event
     * @param category the category of the events // TODO: Decide what categories to have
     * @param status the status of the current event
     */
    public Event(final UUID id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationDeadline, Integer capacity, Integer numOfParticipants, float avgRating, List<Review> reviewList, String category, String status) {
        this.eventId = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationDeadline = registrationDeadline;
        this.capacity = capacity;
        this.numOfParticipants = numOfParticipants;
        this.avgRating = avgRating;
        this.reviewList = reviewList;
        this.category = category;
        this.status = status;
    }

    /**
     *
     * @param title the title of the event (<= 64 characters)
     * @param description the text description of the event (<= ? characters)
     * @param startTime the start time of the event (yyyy-MM-dd hh:mm:ss)
     * @param endTime the end time of the event (yyyy-MM-dd hh:mm:ss)
     * @param registrationDeadline the deadline for event registration (yyyy-MM-dd hh:mm:ss)
     * @param capacity the number of people the event can hold
     * @param numOfParticipants the number of people who signed up for the event (<= capacity)
     * @param avgRating the average rating of the event
     * @param category the category of the events // TODO: Decide what categories to have
     * @param status the status of the current event
     */
    public Event(final UUID id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationDeadline, Integer capacity, Integer numOfParticipants, float avgRating, String category, String status) {
        this.eventId = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationDeadline = registrationDeadline;
        this.capacity = capacity;
        this.numOfParticipants = numOfParticipants;
        this.avgRating = avgRating;
        this.category = category;
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Calculates the vacancy of an event.
     * @return the number of vacancies of the event.
     */
    public Integer calculateVacancy() {
        return this.capacity - this.numOfParticipants;
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
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets the maximum capacity of the event.
     * @param capacity the maximum capacity of the event
     */
    public void setMaxCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the number of participants.
     * @return the number of participants
     */
    public Integer getNumOfParticipants() {
        return numOfParticipants;
    }

    /**
     * Sets the number of participants.
     * @param numOfParticipants the number of participants
     */
    public void setNumOfParticipants(Integer numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    // TODO: Implement calculateAvgRating(List<Review> reviewList)

    /**
     * Gets the average rating of the event.
     * @return average rating
     */
    public float getAvgRating() {
        return avgRating;
    }

}
