package eventsg.backend.service;
import eventsg.backend.dao.RegistrationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class RegistrationService {


    private final RegistrationDao registrationDao;

    @Autowired
    public RegistrationService(@Qualifier("registrationDao") RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    public int registerEvent(UUID userId, UUID eventId) {
        return registrationDao.registerEvent(userId, eventId);
    }

    public int deregisterEvent(UUID userId, UUID eventId) {
        return registrationDao.deregisterEvent(userId, eventId);
    }

    public List<UUID> getRegisteredEvents(UUID userId) {
        return registrationDao.getRegisteredEvents(userId);
    }

    public int getNumOfParticipants(UUID eventId) {
        return registrationDao.getNumOfParticipants(eventId);
    }


}
