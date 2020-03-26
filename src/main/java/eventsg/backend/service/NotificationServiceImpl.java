//package eventsg.backend.service;
//
//import eventsg.backend.dao.RegistrationDao;
//import eventsg.backend.model.Event;
//import eventsg.backend.model.Notification;
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//public class NotificationServiceImpl {
//    private final RegistrationDao registrationDao;
//    private LocalDate today;
//
//    public NotificationServiceImpl(RegistrationDao registrationDao) {
//        this.registrationDao = registrationDao;
//        this.today = LocalDate.now();
//    }
//
//    /**
//     * get list of events which is upcoming in a week
//     *
//     * @param userId id of the user
//     */
//    public List<Notification> getNotificationList(UUID userId) {
//
//        List<UUID> registeredEventIds = registrationDao.getRegisteredEvents(userId);
//        List<Notification> notificationList = new ArrayList<>();
//
//        for (UUID eventId : registeredEventIds) {
//            //if (events already ended) continue;
//            this.addEventToNotificationList(false, notificationList, eventId);
//        }
//        return notificationList;
//    }
//
//    /**
//     * get how many days the event will be holding
//     *
//     * @param event event object
//     * @return days
//     */
//    //this should be called when register an event
//    public int getPeriod(Event event) {
//
//        Period period = Period.between(today, event.getStartTime().toLocalDate());
//        return period.getDays();
//
//    }
//
//    /**
//     * check an event whether should be added to the notification list or not and add it if yes
//     *  @param firstAdded       whether the events is first time notified
//     * @param notificationList the list
//     * @param eventId            event object
//     */
//    public void addEventToNotificationList(Boolean firstAdded, List<Notification> notificationList, UUID eventId) {
//
//        int days = getPeriod(event);
//        if (days > 7) return;
//
//        boolean notify;
//        notify = firstAdded || days == 7 || days == 3 || days == 1 || days == 0;
//        notificationList.add(new Notification(event.getEventId(), event.getStartTime(), days, notify));
//
//        Collections.sort(notificationList);
//    }
//
//    public void deleteEventFromNotificationList(List<Notification> notificationList, Event event) {
//
//        UUID id = event.getEventId();
//        for (int i = 0; i < notificationList.size(); i++) {
//            if (notificationList.get(i).getEventId() == id) {
//                notificationList.remove(i);
//                return;
//            }
//        }
//    }
//}

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
