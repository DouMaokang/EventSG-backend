package eventsg.backend.dao;

import eventsg.backend.mapper.NotificationRowMapper;
import eventsg.backend.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("notificationDao")
public class NotificationDataAccessService implements NotificationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNotification(Notification n) {

        final String sql = "INSERT INTO notification " +
                "(" +
                "notificationId, " +
                "type, " +
                "timeCreated, " +
                "eventId, " +
                "userId" +
                ") VALUES (?, ?, ?, ?, ?)";

        UUID notificationId = UUID.randomUUID();
        jdbcTemplate.update(sql, notificationId, n.getType(), n.getTimeCreated(), n.getEventId(), n.getUserId());
    }

    @Override
    public List<Notification> getNotification(UUID userId, Integer dayLimit) {
        final String sql = "SELECT * FROM notification WHERE userId = ? " +
                "AND timeCreated > (DATE(NOW()) - INTERVAL '7' DAY) ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new NotificationRowMapper());
    }

    @Override
    public List<Notification> getAllNotifications() {
        final String sql = "SELECT * FROM notification";
        return jdbcTemplate.query(sql, new NotificationRowMapper());
    }


}