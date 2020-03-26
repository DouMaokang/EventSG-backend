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

    @PostMapping(path = "registerEvent/{userId}/{eventId}")
    public void registerEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId") UUID userId){
        registrationService.registerEvent(eventId, userId);
    }
    @DeleteMapping(path = "deregisterEvent/{userId}/{eventId}")
    public void deregisterEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId")UUID userId){
        registrationService.deregisterEvent(eventId, userId);
    }

    @GetMapping(path = "getRegisteredEvents/{userId}")
    public List<UUID> getRegisteredEvents(@PathVariable("userId") UUID userId){
        return registrationService.getRegisteredEvents(userId);
    }

    @GetMapping(path = "getNumOfParticipants/{eventId}")
    public int getNumOfParticipants(@PathVariable("eventId") UUID eventId){
        return registrationService.getNumOfParticipants(eventId);
    }

}
