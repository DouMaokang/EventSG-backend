package eventsg.backend.dao;

import eventsg.backend.model.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationDao {

    List<Notification> getReviewNotification(UUID eventId);

    List<Notification> getCapacityNotification(UUID eventId);

    List<Notification> getUpdateNotification(UUID eventId);

    void addNotification(Notification notification);

}