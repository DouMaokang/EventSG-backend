package eventsg.backend.service;
import eventsg.backend.dao.EventRegisterDao;
import eventsg.backend.dao.VenueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class EventRegisterService {


    private final EventRegisterDao eventRegisterDao;

    @Autowired
    public EventRegisterService(@Qualifier("postgresVenue") EventRegisterDao eventRegisterDao) {
        this.eventRegisterDao = eventRegisterDao;
    }

    public int registerEvent(UUID userId, UUID eventId) {
        return EventRegisterDao.registerEvent(userId, eventId);
    }

    public int deregisterEvent(UUID userId, UUID eventId) {
        return EventRegisterDao.deregisterEvent(userId, eventId);
    }


    public List<UUID> getregisteredEvents(UUID userId) {
        return EventRegisterDao.getregisteredEvents(userId);
    }





}
