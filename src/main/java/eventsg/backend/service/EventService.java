package eventsg.backend.service;

import eventsg.backend.dao.EventDao;
import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventDao eventDao;

    @Autowired
    public EventService(@Qualifier("eventDao") EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public void postEvent(Event event) {
        eventDao.postEvent(event);
    }

    public void saveEvent(Event event) {

    }

    public void cancelEvent(UUID eventId) {
        eventDao.cancelEvent(eventId);
    }

    public void deleteEvent(UUID eventId) {
        eventDao.deleteEvent(eventId);
    }

    public void updateEvent(UUID eventId, Event event) {
        eventDao.updateEvent(eventId, event);
    }

    public List<Event> getAllEvent() {
        return eventDao.getAllEvent();
    }

    public Event getEventById(UUID eventId) {
        return eventDao.getEventById(eventId);
    }

    public List<Event> getSavedEvent(UUID userId) {
        return eventDao.getSavedEvent(userId);
    }

    public List<Event> getUpcomingEvent(UUID userId) // registered events
    {
        return eventDao.getUpcomingEvent(userId);
    }

    public List<Event> getPopularEvent() // based on likes/saves
    {
        return eventDao.getPopularEvent();
    }

    public List<Event> getEventByCategory(String category) {
        return eventDao.getEventByCategory(category);
    }


    public List<Event> searchEventByTitle(String keyword) {
        return eventDao.searchEventByTitle(keyword);
    }

//    public void addReview(UUID eventId, Review review) {
//    }

}
