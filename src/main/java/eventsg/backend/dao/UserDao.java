package eventsg.backend.dao;

import eventsg.backend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int insertUser(User user);

    // Basic account operations
    Optional<UUID> login(String email, String password);

    Optional<User> getUserById(UUID id);

    int updateUserById(UUID id, User user);

    int deleteUserById(UUID id);

    // Interested categories
    int addInterestedCategory (UUID userid, String category);

    int deleteInterestedCategory (UUID userid, String category);

    List<String> getInterestedCategories(UUID userid);

    // Saved events
    int saveEvent(UUID userid, UUID eventid);

    int unsaveEvent(UUID userid, UUID eventid);

    List<UUID> getSavedEvents(UUID userId);

}
