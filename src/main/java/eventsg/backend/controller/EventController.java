package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import eventsg.backend.model.User;
import eventsg.backend.model.Venue;
import eventsg.backend.service.EventService;
import eventsg.backend.service.ReviewService;
import eventsg.backend.service.UserService;
import eventsg.backend.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/event")
@RestController
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired // It injects the actual service into the constructor
    public EventController(EventService eventService, VenueService venueService, ReviewService reviewService, UserService userService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.reviewService = reviewService;
        this.userService = userService;
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
        return generateResponseList(eventList);
    }

    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    public Map<String, Object> getEventById(@PathVariable("eventId") UUID eventId) {
        Event event = eventService.getEventById(eventId);
        return generateResponse(event);
    }

    public List<Map<String, Object>> getSavedEvent(UUID userId) {
        List<Event> eventList = eventService.getSavedEvent(userId);
        return this.generateResponseList(eventList);
    }

    private Map<String, Object> generateResponse(Event event) {
        UUID venueId = event.getVenueId();
        Venue venue;
        try {
            venue = venueService.getVenueById(venueId).orElse(null);
        } catch (Exception e) {
            venue = null;
        }
        List<Review> reviewList = reviewService.getReviewsByEventId(event.getEventId());
        User organizer;
        try {
            organizer = userService.getUserById(event.getOrganizerId()).orElse(null);
        } catch (Exception e) {
            organizer = null;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("event", event);
        response.put("organizer", organizer);
        response.put("venue", venue);
        response.put("reviewList", reviewList);
        return  response;
    }

    private List<Map<String, Object>> generateResponseList (List<Event> eventList) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Event event : eventList) {
            responseList.add(generateResponse(event));
        }
        return responseList;
    }

    public List<Map<String, Object>> getUpcomingEvent(UUID userId) // registered events
    {
        List<Event> eventList = eventService.getUpcomingEvent(userId);
        return this.generateResponseList(eventList);
    }

    public List<Event> getPopularEvent() // based on likes/saves
    {
        return eventService.getPopularEvent();
    }

    @GetMapping(path = "category/{category}")
    public List<Map<String, Object>> getEventByCategory(@PathVariable("category") String category) {
        List<Event> eventList = eventService.getEventByCategory(category);
        return this.generateResponseList(eventList);

    }

    @GetMapping(path = "search/{keyword}")
    public List<Map<String, Object>> searchEventByTitle(@PathVariable("keyword") String keyword) {
        List<Event> eventList = eventService.searchEventByTitle(keyword);
        return this.generateResponseList(eventList);
    }

}
