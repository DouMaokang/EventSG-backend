package eventsg.backend.dao;

import eventsg.backend.model.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationDao {

    void addNotification(Notification notification);

    List<Notification> getNotification(UUID userId, Integer dayLimit);

    List<Notification> getAllNotifications();
}