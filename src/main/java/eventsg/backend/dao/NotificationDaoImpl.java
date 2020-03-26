package eventsg.backend.dao;

import eventsg.backend.mapper.NotificationRowMapper;
import eventsg.backend.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNotification(Notification notification) {
        final String sql = "INSERT INTO notification " +
                "(" +
                "notificationId, " +
                "eventId, " +
                "type, " +
                "reviewId, " +
                "capacityLevel, " +
                "time" +

                ") VALUES (?, ?, ?, ?, ?, ?)";


        jdbcTemplate.update(sql,
                notification.getNotificationId(), notification.getEventId(), notification.getType(), notification.getReviewId(),
                notification.getCapacityLevel(),notification.getTime()
        );

    }

    @Override
    public List<Notification> getNotification(UUID eventId,String type) {
        final String sql = "SELECT * FROM notification WHERE eventId = ? AND type = ?";
        List<Notification> notificationList = jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    UUID notificationId = UUID.fromString(resultSet.getString("notificaitonId"));
                    //UUID eventId = UUID.fromString(resultSet.getString("eventId"));
                    //UUID reviewId = resultSet.getString("reviewId");
                    int capacityLevel = resultSet.getString("capacityLevel");
                    LocalDateTime time= resultSet.getString("time");
                    return new Notification(notificationId,eventId,type,reviewId,capacityLevel,time);
                });
        return notificationList;

    }

}