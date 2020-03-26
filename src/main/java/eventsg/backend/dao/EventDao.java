package eventsg.backend.dao;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EventDao {

    void postEvent(Event event);

    void saveEvent(Event event);

    void updateEvent(UUID id, Event event);

    void deleteEvent(UUID id); // delete an event if the event is not published/cancelled

    void cancelEvent(UUID id); // cancel a posted event

    List<Event> getAllEvent();

    Event getEventById(UUID id);

    List<Event> getSavedEvent(UUID userId);

    List<Event> getUpcomingEvent(UUID userId, Integer limit); // registered events

    List<Event> getPopularEvent(); // based on likes/saves

    List<Event> getEventByCategory(String category);

    List<Event> searchEventByTitle(String keyword);


    /** TODO: Review related functions
        void addReview(UUID eventId, Review review);
        List<Review> getAllReview(UUID eventID);
      */

}
