package eventsg.backend.dao;

import eventsg.backend.datasource.Assets;
import eventsg.backend.mapper.EventRowMapper;
import eventsg.backend.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository("eventDao")
public class EventDataAccessService implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID postEvent(Event event) {
        final String sql = "INSERT INTO event " +
                "(" +
                "eventId, " +
                "organizerId, " +
                "title, " +
                "description, " +
                "startTime, " +
                "endTime, " +
                "registrationDeadline, " +
                "capacity, " +
                "numOfParticipants, " +
                "avgRating, " +
                "category, " +
                "status, " +
                "venueId, " +
                "image" +

                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        UUID eventId = UUID.randomUUID();
        UUID organizerId = event.getOrganizerId();

        String title = event.getTitle();
        String description = event.getDescription();
        Timestamp startTime = Timestamp.valueOf(event.getStartTime());
        Timestamp endTime = Timestamp.valueOf(event.getEndTime());
        Timestamp registrationDeadline = Timestamp.valueOf(event.getRegistrationDeadline());
        Integer capacity = event.getCapacity();
        Integer numOfParticipants = event.getNumOfParticipants();
        float avgRating = event.getAvgRating();
        String category = event.getCategory();
        String status = event.getStatus();
        UUID venueId = event.getVenueId();

        String image = Assets.Assets().getEventImage();

        jdbcTemplate.update(sql,
                eventId, organizerId, title, description, startTime, endTime,
                registrationDeadline, capacity, numOfParticipants, avgRating, category,
                status, venueId, image
        );

        return eventId;

    }

    @Override
    public void saveEvent(Event event) {

    }

    /**
     * Updates editable event information.
     * @param eventId the eventId the event to be updated
     * @param event the newly updated event object
     */
    @Override
    public void updateEvent(UUID eventId, Event event) {
        final String sql = "UPDATE event " +
                "SET " +
                "title = ?, " +
                "organizerId = ?, " +
                "description = ?, " +
                "startTime = ?, " +
                "endTime = ?, " +
                "registrationDeadline = ?, " +
                "capacity = ?, " +
                "numOfParticipants = ?, " +
                "category = ?, " +
                "status = ?, " +
                "venueId = ? " +
                "WHERE " +
                "eventId = ?";

        String title = event.getTitle();
        UUID organizerId = event.getOrganizerId();
        String description = event.getDescription();
        Timestamp startTime = Timestamp.valueOf(event.getStartTime());
        Timestamp endTime = Timestamp.valueOf(event.getEndTime());
        Timestamp registrationDeadline = Timestamp.valueOf(event.getRegistrationDeadline());
        Integer capacity = event.getCapacity();
        Integer numOfParticipants = event.getNumOfParticipants();
        String category = event.getCategory();
        String status = event.getStatus();
        UUID venueId = event.getVenueId();


        jdbcTemplate.update(sql,
                // SET
                title, organizerId, description, startTime, endTime, registrationDeadline,
                capacity, numOfParticipants, category, status, venueId,
                // WHERE
                eventId
        );
    }

    @Override
    public void deleteEvent(UUID eventId) {
        final String sql = "DELETE FROM event WHERE eventId = ?";
        jdbcTemplate.update(sql, eventId);
    }

    @Override
    public void cancelEvent(UUID eventId) {
        final String sql = "UPDATE event " +
                "SET " +
                "status = ? " +
                "WHERE " +
                "eventId = ?";
        jdbcTemplate.update(sql, "cancelled", eventId);
    }

    @Override
    public List<Event> getAllEvent() {
        final String sql = "SELECT * FROM event ORDER BY startTime";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    @Override
    public Event getEventById(UUID eventId) {
        final String sql = "SELECT * FROM event WHERE eventId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, new EventRowMapper());
    }

    @Override
    public List<Event> getSavedEvent(UUID userId) {
        final String sql = "SELECT * FROM event WHERE eventId IN " +
                "(SELECT eventId FROM savedEvent WHERE userId = ?) " +
                "ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }

    @Override
    public List<Event> getUpcomingEvent(UUID userId, Integer limit) {
        final String sql = "SELECT * FROM event " +
                "WHERE eventId IN (SELECT eventId FROM registration WHERE userId = ?) "+
                "AND startTime < (DATE(NOW()) + INTERVAL '7' DAY) AND startTime >= DATE(NOW())";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }

    // TODO: To have an additional attribute "numOfSaves"?
    @Override
    public List<Event> getPopularEvent() {
        final String sql = "SELECT * FROM event WHERE eventId IN " +
                "(SELECT eventId FROM savedEvent GROUP BY eventId HAVING COUNT(*) > 20" +
                ") ORDER BY startTime";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    @Override
    public List<Event> getEventByCategory(String category) {
        final String sql = "SELECT * FROM event WHERE category = ? ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{category}, new EventRowMapper());
    }

    @Override
    public List<Event> searchEventByTitle(String keyword) {
        final String sql = "SELECT * FROM event WHERE LOWER(title) LIKE '%" + keyword.toLowerCase() + "%' ORDER BY startTime";
        // return events whose title contains the keyword
        return jdbcTemplate.query(sql, new EventRowMapper());

    }

    @Override
    public boolean hasSavedEvent(UUID eventId, UUID userId) {
        final String sql = "SELECT COUNT(*) FROM user_saved_event WHERE eventId = ? AND userId = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{eventId, userId}, Integer.class);
        System.out.println("eventId: " + eventId);
        System.out.println("userId: " + userId);
        System.out.println(count);

        return (count > 0);
    }

    @Override
    public List<Event> getOrganizedEvent(UUID userId) {
        final String sql =  "SELECT * FROM event WHERE organizerId = ? ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }
}
