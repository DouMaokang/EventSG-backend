package eventsg.backend.mapper;

import eventsg.backend.model.Event;
import eventsg.backend.model.Notification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationRowMapper implements RowMapper<Notification> {

    @Override
    public Notification mapRow(ResultSet resultSet, int i) throws SQLException {

        UUID notificationId = UUID.fromString(resultSet.getString("notificationId"));
        String type = resultSet.getString("type");
        LocalDateTime timeCreated = resultSet.getTimestamp("timeCreated").toLocalDateTime();
        UUID eventId = UUID.fromString(resultSet.getString("eventId"));
        UUID userId = UUID.fromString(resultSet.getString("userId"));


        return new Notification(notificationId, type, timeCreated, eventId, userId);
    }
}
