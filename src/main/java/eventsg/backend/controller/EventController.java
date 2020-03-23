package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import eventsg.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/event")
@RestController
public class EventController {

    private final EventService eventService;

    @Autowired // It injects the actual service into the constructor
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void postEvent(@RequestBody Event event) {
        eventService.postEvent(event);
    }

    public void saveEvent(Event event) {

    }

    @PutMapping(path = "cancel/{eventId}")
    public void cancelEvent(@PathVariable("eventId") UUID eventId) {
        eventService.cancelEvent(eventId);
    }

    @DeleteMapping(path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") UUID eventId) {
        eventService.deleteEvent(eventId);
    }

    @PutMapping(path = "{eventId}")
    public void updateEvent(@PathVariable("eventId") UUID eventId, @RequestBody Event event) {
        eventService.updateEvent(eventId, event);
    }

    @GetMapping()
    public List<Event> getAllEvent() {
        return eventService.getAllEvent();
    }

    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable("eventId") UUID eventId) {
        return eventService.getEventById(eventId);
    }

    public List<Event> getSavedEvent(UUID userId) {
        return eventService.getSavedEvent(userId);
    }

    public List<Event> getUpcomingEvent(UUID userId) // registered events
    {
        return eventService.getUpcomingEvent(userId);
    }

    public List<Event> getPopularEvent() // based on likes/saves
    {
        return eventService.getPopularEvent();
    }

    @GetMapping(path = "category/{category}")
    public List<Event> getEventByCategory(@PathVariable("category") String category) {
        return eventService.getEventByCategory(category);
    }

    @GetMapping(path = "search/{keyword}")
    public List<Event> searchEventByTitle(@PathVariable("keyword") String keyword) {
        return eventService.searchEventByTitle(keyword);
    }

//    public void addReview(UUID eventId, Review review) {
//    }

}
