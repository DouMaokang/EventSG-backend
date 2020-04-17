package eventsg.backend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import eventsg.backend.model.Event;
import eventsg.backend.model.Notification;
import eventsg.backend.service.EventService;
import eventsg.backend.service.NotificationService;
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
    private final NotificationService notificationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, EventService eventService,
                                  NotificationService notificationService) {
        this.registrationService = registrationService;
        this.eventService = eventService;
        this.notificationService = notificationService;
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
        notificationService.addNotification("registration", eventId, eventService.getEventById(eventId).getOrganizerId());
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
        notificationService.addNotification("deregistration", eventId, eventService.getEventById(eventId).getOrganizerId());

    }

    /**
     * Checks whether a user has registered a particular event.
     * @param eventId the id of the event
     * @param userId the id of the user
     * @return true if the user has registered the event
     */
    @GetMapping(path = "check/{eventId}/{userId}")
    public boolean hasRegistered(@PathVariable("eventId") UUID eventId, @PathVariable("userId")UUID userId) {
        return registrationService.hasRegistered(eventId, userId);
    }


}
