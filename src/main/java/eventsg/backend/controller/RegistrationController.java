package eventsg.backend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import eventsg.backend.model.Event;
import eventsg.backend.service.EventService;
import eventsg.backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/registration")
@RestController

public class RegistrationController {

    private final RegistrationService registrationService;
    private final EventService eventService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, EventService eventService) {
        this.registrationService = registrationService;
        this.eventService = eventService;
    }

    /**
     * Creates a new registration record.
     * @param eventId the id of the event registered
     * @param userId the id of the user who registered the event
     */
    @RequestMapping(path = "add/{eventId}/{userId}", method = RequestMethod.POST)
    public void registerEvent(@Valid @NotNull @PathVariable("eventId") UUID eventId, @Valid @NotNull @PathVariable("userId") UUID userId){
        registrationService.registerEvent(userId, eventId);
        Event event = eventService.getEventById(eventId);
        event.setNumOfParticipants(event.getNumOfParticipants() + 1);
        eventService.updateEvent(eventId, event);
    }

    /**
     * Cancels the registration of an event.
     * @param eventId the id of the event registered
     * @param userId the id of the user who registered the event
     */
    @DeleteMapping(path = "cancel/{eventId}/{userId}")
    public void deregisterEvent(@PathVariable("eventId") UUID eventId, @PathVariable("userId")UUID userId){
        registrationService.deregisterEvent(userId, eventId);
        Event event = eventService.getEventById(eventId);
        event.setNumOfParticipants(event.getNumOfParticipants() - 1);
        eventService.updateEvent(eventId, event);

    }

    @GetMapping(path = "check/{eventId}/{userId}")
    public boolean hasRegistered(@PathVariable("eventId") UUID eventId, @PathVariable("userId")UUID userId) {
        return registrationService.hasRegistered(eventId, userId);
    }

    // Moved into event controller
//    /**
//     * Returns all registration record of a user.
//     * @param userId the id of the user
//     * @return a list of registrations
//     */
//    @GetMapping(path = "userId/{userId}")
//    public List<UUID> getRegisteredEvents(@PathVariable("userId") UUID userId){
//        return registrationService.getRegisteredEvents(userId);
//    }


// TODO: update the no. of participants in the database

//    @GetMapping(path = "getNumOfParticipants/{eventId}")
//    public int getNumOfParticipants(@PathVariable("eventId") UUID eventId){
//        return registrationService.getNumOfParticipants(eventId);
//    }



}
