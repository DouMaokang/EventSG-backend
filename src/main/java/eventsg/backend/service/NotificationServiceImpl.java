package eventsg.backend.service;

import eventsg.backend.model.Event;
import eventsg.backend.model.Notification;

import javax.management.Notification;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotificationServiceImpl {

    private final RegistrationDao registrationDao;
    private final EventDao eventDao;
    private final NotificationDao notificationDao;

    public NotificationServiceImpl(RegistrationDao registrationDao, EventDao eventDao, NotificationDao notificationDao) {
        this.registrationDao = registrationDao;
        this.eventDao = eventDao;
        this.notificationDao = notificationDao;
    }

    public List<Notification> getNotificationList(UUID userId) {

        List<Notification> notificationList = new ArrayList<>();
        List<Event> organizedEvents=EventDao.getOrganizedEvents(userId);
        List<Event> registeredEvents=RegistrationDao.getRegisteredEvents(userId);

        for (Event event:organizedEvents) {
            UUID eventId=event.eventId;
            notificationList.add(notificationDao.getReviewNotification(eventId));
            notificationList.add(notificationDao.getCapacityNotification(eventId));
        }

        for (Event event:registeredEvents) {
            UUID eventId=event.eventId;
            notificationList.add(notificationDao.getUpdateNotification(eventId));
        }

        return notificationList;
    }

    public void addReviewNotification(UUID eventId, UUID reviewId) {
        //when add a review QUESTION??? how to receive these parameters? from review service?
        //it must go through review service to get the reviewId.
    }
    public void addCapacityNotification(UUID eventId, int capacityLevel) {
        //when register an event
    }
    public void addUpdateNotification(UUID eventId) {
        //when update an event
    }
}
