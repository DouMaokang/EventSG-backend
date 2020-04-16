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

    /**
     * Add the event to the database and returns the event UUID.
     * @param event An event object.
     * @return UUID of the posted event.
     */
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

    /**
     * Save the draft of an event that is created but not yet posted.
     * @param event An event object.
     */
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

    /**
     * Delete an event if the event is not published/cancelled
     * @param eventId UUID of event to be deleted.
     */
    @Override
    public void deleteEvent(UUID eventId) {
        final String sql = "DELETE FROM event WHERE eventId = ?";
        jdbcTemplate.update(sql, eventId);
    }

    /**
     * Cancel a posted event
     * @param eventId UUID of event to be cancelled.
     */
    @Override
    public void cancelEvent(UUID eventId) {
        final String sql = "UPDATE event " +
                "SET " +
                "status = ? " +
                "WHERE " +
                "eventId = ?";
        jdbcTemplate.update(sql, "cancelled", eventId);
    }

    /**
     * Get all events from the database.
     * @return A list of events.
     */
    @Override
    public List<Event> getAllEvent() {
        final String sql = "SELECT * FROM event ORDER BY startTime";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    /**
     * Get a single event by its UUID.
     * @param eventId UUID of the event.
     * @return An event object.
     */
    @Override
    public Event getEventById(UUID eventId) {
        final String sql = "SELECT * FROM event WHERE eventId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, new EventRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Return a list of events liked/saved by the user.
     * @param userId UUID of the queried user.
     * @return A list of event objects.
     */
    @Override
    public List<Event> getSavedEvent(UUID userId) {
        final String sql = "SELECT * FROM event WHERE eventId IN " +
                "(SELECT eventId FROM savedEvent WHERE userId = ?) " +
                "ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }

    /**
     * Get all events registered by the user that will occur in a number of days sepcified.
     * @param userId UUID of user
     * @param limit the number of days.
     * @return a list of event objects.
     */
    @Override
    public List<Event> getUpcomingEvent(UUID userId, Integer limit) {
        final String sql = "SELECT * FROM event " +
                "WHERE eventId IN (SELECT eventId FROM registration WHERE userId = ?) "+
                "AND startTime < (DATE(NOW()) + INTERVAL '7' DAY) AND startTime >= DATE(NOW())";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }

    /**
     * Get a list of popular events based on number of likes.
     * @return a list of objects.
     */
    // TODO: To have an additional attribute "numOfSaves"?
    @Override
    public List<Event> getPopularEvent() {
        final String sql = "SELECT * FROM event WHERE eventId IN " +
                "(SELECT eventId FROM savedEvent GROUP BY eventId HAVING COUNT(*) > 20" +
                ") ORDER BY startTime";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    /**
     * Get all events belonging to the category specified.
     * @param category the name of the category.
     * @return a list of events.
     */
    @Override
    public List<Event> getEventByCategory(String category) {
        final String sql = "SELECT * FROM event WHERE category = ? ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{category}, new EventRowMapper());
    }

    /**
     * Search for events that have matching keywords in their title.
     * @param keyword keywords for search.
     * @return a list of matching events.
     */
    @Override
    public List<Event> searchEventByTitle(String keyword) {
        final String sql = "SELECT * FROM event WHERE LOWER(title) LIKE '%" + keyword.toLowerCase() + "%' ORDER BY startTime";
        // return events whose title contains the keyword
        return jdbcTemplate.query(sql, new EventRowMapper());

    }

    /**
     * Check whether the user has liked the event or not.
     * @param eventId event to be checked.
     * @param userId user to be checked.
     * @return True if user has saved the event.
     */
    @Override
    public boolean hasSavedEvent(UUID eventId, UUID userId) {
        final String sql = "SELECT COUNT(*) FROM user_saved_event WHERE eventId = ? AND userId = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{eventId, userId}, Integer.class);
        System.out.println("eventId: " + eventId);
        System.out.println("userId: " + userId);
        System.out.println(count);

        return (count > 0);
    }

    /**
     * Get all events organised by the user.
     * @param userId the UUID of the user.
     * @return all organised events organised by the user.
     */
    @Override
    public List<Event> getOrganizedEvent(UUID userId) {
        final String sql =  "SELECT * FROM event WHERE organizerId = ? ORDER BY startTime";
        return jdbcTemplate.query(sql, new Object[]{userId}, new EventRowMapper());
    }
}
