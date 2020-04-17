package eventsg.backend.dao;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EventDao {

    /**
     * Add the event to the database and returns the event UUID.
     * @param event An event object.
     * @return UUID of the posted event.
     */
    UUID postEvent(Event event);

    /**
     * Save the draft of an event that is created but not yet posted.
     * @param event An event object.
     */
    void saveEvent(Event event);

    /**
     * Update the event given the id with the provided event.
     * @param id UUID of the event to be updated.
     * @param event An event object to replace the old event object.
     */
    void updateEvent(UUID id, Event event);

    /**
     * Delete an event if the event is not published/cancelled
     * @param id UUID of event to be deleted.
     */
    void deleteEvent(UUID id);

    /**
     * Cancel a posted event
     * @param id UUID of event to be cancelled.
     */
    void cancelEvent(UUID id); // cancel a posted event

    /**
     * Get all events from the database.
     * @return A list of events.
     */
    List<Event> getAllEvent();

    /**
     * Get a single event by its UUID.
     * @param id UUID of the event.
     * @return An event object.
     */
    Event getEventById(UUID id);

    /**
     * Return a list of events liked/saved by the user.
     * @param userId UUID of the queried user.
     * @return A list of event objects.
     */
    List<Event> getSavedEvent(UUID userId);

    /**
     * Get all events registered by the user that will occur in a number of days sepcified.
     * @param userId UUID of user
     * @param limit the number of days.
     * @return a list of event objects.
     */
    List<Event> getUpcomingEvent(UUID userId, Integer limit); // registered events

    /**
     * Get a list of popular events based on number of likes.
     * @return a list of objects.
     */
    List<Event> getPopularEvent(); // based on likes/saves

    /**
     * Get all events belonging to the category specified.
     * @param category the name of the category.
     * @return a list of events.
     */
    List<Event> getEventByCategory(String category);

    /**
     * Search for events that have matching keywords in their title.
     * @param keyword keywords for search.
     * @return a list of matching events.
     */
    List<Event> searchEventByTitle(String keyword);

    /**
     * Check whether the user has liked the event or not.
     * @param eventId event to be checked.
     * @param userId user to be checked.
     * @return True if user has saved the event.
     */
    boolean hasSavedEvent(UUID eventId, UUID userId);

    /**
     * Get all events organised by the user.
     * @param userId the UUID of the user.
     * @return all organised events organised by the user.
     */
    List<Event> getOrganizedEvent(UUID userId);

    /** TODO: Review related functions
        void addReview(UUID eventId, Review review);
        List<Review> getAllReview(UUID eventID);
      */

}
