package eventsg.backend.dao;

import eventsg.backend.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    /**
     * Insert a new account into the user table.
     * @param user the new user account
     * @return 0 or 1 (number of rows affected)
     */
    int addUser(User user);

    /**
     * Check the Validity of the login information provided. If the email address and the password can be found in the
     * database, it will return the found userId.
     * @param email Login email address
     * @param password Login password
     * @return If successful, returns a UUID, otherwise returns null.
     */
    UUID login(String email, String password);

    /**
     * Get user by userId
     * @param id userId
     * @return if a matching user is found, return found User; else return null.
     */
    User getUserById(UUID id);

    /**
     * Get all users in the database.
     * @return a list of users.
     */
    List<User> getAllUser();

    /**
     * Update user account info.
     * @param id userId
     * @param user updated user account.
     * @return TODO need to combine update statements
     */
    int updateUserById(UUID id, User user);

    /**
     * deletes an account from the user table.
     * @param id userId to be deleted
     * @return 0 or 1. 1 means successful.
     */
    int deleteUserById(UUID id);

    /**
     * Add a record of interested category in the user_interested_category table.
     * @param userId UUID
     * @param category interested category
     * @return 0 or 1
     */
    int addInterestedCategory (UUID userId, String category);

    /**
     * Delete a record of interested category from the database.
     * @param userId UUID
     * @param category category no longer interested in
     * @return 0 or 1
     */
    int deleteInterestedCategory (UUID userId, String category);

    /**
     * Get a list of categories that user of userid is insterested in.
     * @param userId UUID
     * @return a list of categories
     */
    List<String> getInterestedCategories(UUID userId);

    /**
     * Add a record of (userId, eventId) into user_saved_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    int saveEvent(UUID userId, UUID eventId);

    /**
     * Delete a record of (userId, eventId) from user_saved_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    int unsaveEvent(UUID userId, UUID eventId);

    /**
     * Get a list of eventIds that are saved by a user.
     * @param userId UUID
     * @return a list of eventIds.
     */
    List<UUID> getSavedEvents(UUID userId);

}