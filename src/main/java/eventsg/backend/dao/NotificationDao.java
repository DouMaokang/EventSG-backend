package eventsg.backend.dao;

import eventsg.backend.model.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationDao {

    /**
     * Add notification to the database.
     * @param notification the notification to be added.
     */
    void addNotification(Notification notification);

    /**
     * Get a list of notifications which should be sent to users.
     * @param userId UUID of user.
     * @param dayLimit number of days in which events are going to take place.
     * @return
     */
    List<Notification> getNotification(UUID userId, Integer dayLimit);

    /**
     * Get all notifications.
     * @return all notifications.
     */
    List<Notification> getAllNotifications();
}