package eventsg.backend.controller;

import eventsg.backend.service.EventRegisterService;
import eventsg.backend.service.VenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/venue")
@RestController

public class EventRegisterController {

    private final EventRegisterService eventRegisterService;

    @Autowired
    public EventRegisterController(EventRegisterService eventRegisterService) {
        this.eventRegisterService = eventRegisterService;
    }

    @PostMapping(path = "registerEvent/{userId}/{eventId}")
    public void registerEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId") UUID userId){
        eventRegisterService.registerEvent(eventId, userId);
    }
    @DeleteMapping(path = "deregisterEvent/{userId}/{eventId}")
    public void deregisterEvent(@PathVariable("userId") UUID eventId, @PathVariable("eventId")UUID userId){
        eventRegisterService.deregisterEvent(eventId, userId);
    }

    @GetMapping(path = "getRegisteredEvents/{userId}")
    public List<UUID> getregisteredEvents(@PathVariable("userId") UUID userId){
        return eventRegisterService.getregisteredEvents(userId);
    }






}
