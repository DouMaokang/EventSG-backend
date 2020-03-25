package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import eventsg.backend.model.Venue;
import eventsg.backend.service.EventService;
import eventsg.backend.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/event")
@RestController
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;

    @Autowired // It injects the actual service into the constructor
    public EventController(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;
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
    public List<Map<String, Object>> getAllEvent() {
        List<Event> eventList = eventService.getAllEvent();
        System.out.println(eventList.size());
        List<Map<String, Object>> response = new ArrayList<>();

        for (int i = 0; i < eventList.size(); i++) {

            Event event = eventList.get(i);
            System.out.println(event.getTitle());
            System.out.println(event.getVenueId());

            Venue venue = venueService.getVenueById(event.getVenueId()).get();

            System.out.println(venue.getAddress());

            Map<String, Object> item = new HashMap<>();
            item.put("event", event);
            item.put("venue", venue);
            response.add(item);
        }
        return response;
    }

    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    public Map<String, Object> getEventById(@PathVariable("eventId") UUID eventId) {
        Event myEvent = eventService.getEventById(eventId);
        UUID venueId = myEvent.getVenueId();
        Venue myVenue = venueService.getVenueById(venueId).orElse(null);

        Map<String, Object> response = new HashMap<>();
        response.put("event", myEvent);
        response.put("venue", myVenue);

        return response;
    }

    public List<Map<String, Object>> getSavedEvent(UUID userId) {
        List<Event> eventList = eventService.getSavedEvent(userId);
        return this.generateResponse(eventList);
    }

    private List<Map<String, Object>> generateResponse(List<Event> eventList) {
        List<Map<String, Object>> response = new ArrayList<>();

        for (Event event : eventList) {
            Venue venue = venueService.getVenueById(event.getVenueId()).orElse(null);
            Map<String, Object> item = new HashMap<>();
            item.put("event", event);
            item.put("venue", venue);
            response.add(item);
        }
        return response;
    }

    public List<Map<String, Object>> getUpcomingEvent(UUID userId) // registered events
    {
        List<Event> eventList = eventService.getUpcomingEvent(userId);
        return this.generateResponse(eventList);
    }

    public List<Event> getPopularEvent() // based on likes/saves
    {
        return eventService.getPopularEvent();
    }

    @GetMapping(path = "category/{category}")
    public List<Map<String, Object>> getEventByCategory(@PathVariable("category") String category) {
        List<Event> eventList = eventService.getEventByCategory(category);
        return this.generateResponse(eventList);

    }

    @GetMapping(path = "search/{keyword}")
    public List<Map<String, Object>> searchEventByTitle(@PathVariable("keyword") String keyword) {
        List<Event> eventList = eventService.searchEventByTitle(keyword);
        return this.generateResponse(eventList);

    }

//    public void addReview(UUID eventId, Review review) {
//    }

}
