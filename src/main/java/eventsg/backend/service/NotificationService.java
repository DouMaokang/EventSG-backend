package eventsg.backend.service;

import eventsg.backend.dao.NotificationDao;
import eventsg.backend.model.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationDao notificationDao;

    public NotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    /**
     * Returns a list of notifications in the past few days.
     * @param userId the id of the user to whom the notification list belongs
     * @param dayLimit to limit the returned notification to be in the past several days
     * @return
     */
    public List<Notification> getNotification(UUID userId, Integer dayLimit) {
        return notificationDao.getNotification(userId, dayLimit);
    }

    public void addNotification(String type, UUID eventId, UUID userId) {
        LocalDateTime timeCreated = LocalDateTime.now();
        notificationDao.addNotification(new Notification(type, timeCreated, eventId, userId));
    }

}
