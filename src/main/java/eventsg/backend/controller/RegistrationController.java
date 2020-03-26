package eventsg.backend.controller;

import eventsg.backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/registration")
@RestController

public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Creates a new registration record.
     * @param eventId the id of the event registered
     * @param userId the id of the user who registered the event
     */
    @PostMapping(path = "add/{userId}/{eventId}")
    public void registerEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId") UUID userId){
        registrationService.registerEvent(eventId, userId);
    }

    /**
     * Deregisters an event.
     * @param eventId the id of the event registered
     * @param userId the id of the user who registered the event
     */
    @DeleteMapping(path = "cancel/{userId}/{eventId}")
    public void deregisterEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId")UUID userId){
        registrationService.deregisterEvent(eventId, userId);
    }

    /**
     * Returns all registration record of a user.
     * @param userId the id of the user
     * @return a list of registrations
     */
    @GetMapping(path = "{userId}")
    public List<UUID> getRegisteredEvents(@PathVariable("userId") UUID userId){
        return registrationService.getRegisteredEvents(userId);
    }

// TODO: update the no. of participants in the database

//    @GetMapping(path = "getNumOfParticipants/{eventId}")
//    public int getNumOfParticipants(@PathVariable("eventId") UUID eventId){
//        return registrationService.getNumOfParticipants(eventId);
//    }

}
