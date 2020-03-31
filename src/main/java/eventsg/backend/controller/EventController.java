package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Review;
import eventsg.backend.model.User;
import eventsg.backend.model.Venue;
import eventsg.backend.service.*;
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
    private final RegistrationService registrationService;
    private final NotificationService notificationService;

    @Autowired // It injects the actual service into the constructor
    public EventController(EventService eventService, VenueService venueService,
                           ReviewService reviewService, UserService userService,
                           RegistrationService registrationService,
                           NotificationService notificationService
                           ) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.registrationService = registrationService;
        this.notificationService = notificationService;
    }

    /**
     * Receives a new event and stores it in the database.
     * @param event the event object sent from client side
     */
    @RequestMapping(path = "add", method = RequestMethod.POST)
    public void postEvent(@RequestBody Event event) {
        UUID eventId = eventService.postEvent(event);
        System.out.println(event.getVenueId());
        System.out.println(event.getEventId());
        UUID venueOwnerId = venueService.getVenueByEventId(eventId).getOwnerId();

        // TODO: test notification
        // Notify the venue owner that his/her venue is rented.
        notificationService.addNotification("venue", eventId, venueOwnerId);
        // Notify users who previously registered this organizer's events.
        List<UUID> usersToNotify = new ArrayList<>();
        List<Event> pastEvents = eventService.getOrganizedEvent(event.getOrganizerId());
        for (Event pastEvent : pastEvents) {
            usersToNotify.addAll(registrationService.getRegisteredUsers(pastEvent.getEventId()));
        }
        Set<UUID> set = new HashSet<UUID>(usersToNotify);
        List<UUID> uniqueUsersToNotify = new ArrayList<>();
        uniqueUsersToNotify.addAll(set);
        for (UUID uuid : uniqueUsersToNotify) {
            notificationService.addNotification("event", eventId, uuid);
        }
    }

    /**
     * Receives a partially completed event as a draft event.
     * @param event the event object sent from client side
     */
    @RequestMapping(path = "add-draft", method = RequestMethod.POST)
    public void saveDraftEvent(@RequestBody Event event) {

    }

    /**
     * Cancels a posted event.
     * @param eventId the id of the event to be canceled
     */
    @PutMapping(path = "cancel/{eventId}")
    public void cancelEvent(@PathVariable("eventId") UUID eventId) {
        eventService.cancelEvent(eventId);
    }

    /**
     * Deletes a draft event.
     * @param eventId the id of the draft event to be deleted
     */
    @DeleteMapping(path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") UUID eventId) {
        eventService.deleteEvent(eventId);
    }

    /**
     * Updates the details of an event.
     * @param eventId the id of the event to be updated
     * @param event the new event object with updated attributes
     */
    @PutMapping(path = "{eventId}")
    public void updateEvent(@PathVariable("eventId") UUID eventId, @RequestBody Event event) {
        eventService.updateEvent(eventId, event);
    }

    /**
     * Returns all events posted.
     * @return all events ever posted
     */
    @GetMapping()
    public List<Map<String, Object>> getAllEvent() {
        List<Event> eventList = eventService.getAllEvent();
        return generateResponseList(eventList);
    }

    /**
     * Returns a particular event.
     * @param eventId the id of the event
     * @return the requested event
     */
    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    public Map<String, Object> getEventById(@PathVariable("eventId") UUID eventId) {
        Event event = eventService.getEventById(eventId);
        return generateResponse(event);
    }

    /**
     * Returns all registration record of a user.
     * @param userId the id of the user
     * @return a list of registrations
     */
    @GetMapping(path = "registered/{userId}")
    public List<Map<String, Object>> getRegisteredEvents(@PathVariable("userId") UUID userId){
        List<UUID> eventIds = registrationService.getRegisteredEvents(userId);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < eventIds.size(); i++) {
            eventList.add(eventService.getEventById(eventIds.get(i)));
        }
        return generateResponseList(eventList);
    }


    /**
     * Return a list of upcoming events which the user has registered.
     * @param userId the id of the user.
     * @return a list of events
     */
    @RequestMapping(value = "upcoming/{userId}/{limit}", method = RequestMethod.GET)
    public List<Map<String, Object>> getUpcomingEvent(@PathVariable("userId") UUID userId,
                                                      @PathVariable("limit") Integer limit) // registered events
    {
        List<Event> eventList = eventService.getUpcomingEvent(userId, limit);
        return this.generateResponseList(eventList);
    }


    /**
     * Returns a list of events recommended based on the user's interests.
     * @param userId the id of the user
     * @return a list of events
     */
    @GetMapping(path = "recommended/{userId}")
    public List<Map<String, Object>> getRecommendedEvent(@PathVariable("userId") UUID userId) {
        List<String> interestedCategories = userService.getInterestedCategories(userId);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < interestedCategories.size(); i++) {
            List<Event> tempList = eventService.getEventByCategory(interestedCategories.get(i));
            eventList.addAll(tempList);
        }
        return this.generateResponseList(eventList);
    }

    /**
     * Returns a list of events of a particular category.
     * @param category the category of the event
     * @return a list of events
     */
    @GetMapping(path = "category/{category}")
    public List<Map<String, Object>> getEventByCategory(@PathVariable("category") String category) {
        List<Event> eventList = eventService.getEventByCategory(category);
        return this.generateResponseList(eventList);
    }

    /**
     * Returns a list of events whose title is similar to the input keyword.
     * @param keyword the keyword used to search for events
     * @return a list of events
     */
    @GetMapping(path = "search/{keyword}")
    public List<Map<String, Object>> searchEventByTitle(@PathVariable("keyword") String keyword) {
        List<Event> eventList = eventService.searchEventByTitle(keyword);
        return this.generateResponseList(eventList);
    }

    @GetMapping(path = "has_saved/eventId={eventId}/userId={userId}")
    public boolean hasSavedEvent(@PathVariable("eventId") UUID eventId, @PathVariable("userId") UUID userId) {
        return  eventService.hasSavedEvent(eventId, userId);
    }


    @PostMapping(path = "save_event/eventId={eventId}/userId={userId}")
    public void saveEvent(@PathVariable("userId") UUID userId, @PathVariable("eventId") UUID eventId){
        userService.saveEvent(userId, eventId);
    };

    @DeleteMapping(path = "unsave_event/eventId={eventId}/userId={userId}")
    public void unsaveEvent(@PathVariable("userId") UUID userId, @PathVariable("eventId")UUID eventId){
        userService.unsaveEvent(userId, eventId);
    };

    @GetMapping(path = "all_saved_events/userId={userId}")
    public List<Map<String, Object>> getSavedEvents(@PathVariable("userId") UUID userId) {
        List<UUID> eventIds = userService.getSavedEvents(userId);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < eventIds.size(); i++) {
          eventList.add(eventService.getEventById(eventIds.get(i)));
        }
        return this.generateResponseList(eventList);
    }

    @GetMapping(path = "organizer/userId={userId}")
    public List<Map<String, Object>> getEventByOrganizer(@PathVariable("userId") UUID userId) {
        List<Event> eventList = eventService.getOrganizedEvent(userId);
        return this.generateResponseList(eventList);
    }

    private Map<String, Object> generateResponse(Event event) {
        UUID venueId = event.getVenueId();
        Map<String, Object> venue = new HashMap<>();
        try {
            venue.put("venue", venueService.getVenueById(venueId));
            venue.put("owner", userService.getUserById(venueService.getVenueById(venueId).getOwnerId()));
        } catch (Exception e) {
            venue = null; // todo i removed the Optional from VenueDao
        }
        List<Review> reviewList = reviewService.getReviewsByEventId(event.getEventId());
        User organizer;
        try {
            organizer = userService.getUserById(event.getOrganizerId());
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

}
