package eventsg.backend.dao;
import eventsg.backend.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("registrationDao")

public class RegistrationAccessService implements RegistrationDao {

    private final JdbcTemplate jdbcTemplate;

    public RegistrationAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * Add a record of (userId, eventId) into user_registered_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */

    @Override
    public int registerEvent(UUID userId, UUID eventId) {
        return jdbcTemplate.update("INSERT INTO registration(userId, eventId) VALUES(?,?)", userId, eventId);
    }


    /**
     * Delete a record of (userId, eventId) from user_registered_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    @Override
        public int deregisterEvent(UUID userId, UUID eventId) {
            return jdbcTemplate.update("DELETE FROM registration WHERE userId = ? AND eventId = ?", userId, eventId);

    }


    /**
     * Get a list of eventIds that are registered by a user.
     * @param userId UUID
     * @return a list of eventIds.
     */
    @Override
    public List<UUID> getRegisteredEvents(UUID userId) {
        final String sql = "SELECT eventId FROM registration WHERE userId = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{userId},
                (resultSet, i) -> {
                    UUID eventId  = UUID.fromString(resultSet.getString("eventId"));
                    return eventId;
                });
    }

    @Override
    public int getNumOfParticipants(UUID eventId) {
        final String sql = "SELECT COUNT(*) FROM registration WHERE eventId = " + eventId;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean hasRegistered(UUID eventId, UUID userId) {
        final String sql = "SELECT COUNT(*) FROM registration WHERE eventId = ? AND userId = ?";
        System.out.println("eventId: " + eventId);
        System.out.println("userId: " + userId);
        int count = jdbcTemplate.queryForObject(sql, new Object[]{eventId, userId}, Integer.class);
        return (count > 0);
    }

}
