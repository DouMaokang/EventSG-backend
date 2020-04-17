package eventsg.backend.dao;

import eventsg.backend.model.User;
import eventsg.backend.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistrationDao {
    /**
     * Add a record of (userId, eventId) into user_registered_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    int registerEvent(UUID userId, UUID eventId);

    /**
     * Delete a record of (userId, eventId) from user_registered_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    int deregisterEvent(UUID userId, UUID eventId);

    /**
     * Get a list of eventIds that are registered by a user.
     * @param userId UUID
     * @return a list of eventIds.
     */
    List<UUID> getRegisteredEvents(UUID userId);

    /**
     * Get number of users who have registered.
     * @param eventId UUID of the event.
     * @return the number of participants.
     */
    int getNumOfParticipants(UUID eventId);

    /**
     * Check whether the user has registered for the event.
     * @param eventId UUID of the event.
     * @param userId UUID of the user.
     * @return True of the user has registered.
     */
    boolean hasRegistered(UUID eventId, UUID userId);

    /**
     * Get users who have registered for the event.
     * @param eventId UUID of the event.
     * @return A list of users who have registered.
     */
    List<UUID> getRegisteredUsers(UUID eventId);


}



