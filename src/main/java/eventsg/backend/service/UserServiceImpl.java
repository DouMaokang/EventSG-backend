package eventsg.backend.service;

import eventsg.backend.dao.UserDao;
import eventsg.backend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    int insertUser(User user){
        return userDao.insertUser(user);
    };

    Optional<UUID> login(String email, String password){
        return userDao.login(email, password);
    };

    Optional<User> getUserById(UUID id){
        return userDao.getUserById(id);
    };

    int updateUserById(UUID id, User user){
        return userDao.updateUserById(id, user);
    };

    int deleteUserById(UUID id){
        return deleteUserById(id);
    };

    int addInterestedCategory (UUID userid, String category){
        return addInterestedCategory(userid, category);
    };

    int deleteInterestedCategory (UUID userid, String category){
        return deleteInterestedCategory(userid, category);
    };

    List<String> getInterestedCategories(UUID userid){
        return getInterestedCategories(userid);
    };

    int saveEvent(UUID userid, UUID eventid){
        return saveEvent(userid, eventid);
    };

    int unsaveEvent(UUID userid, UUID eventid){
        return unsaveEvent(userid, eventid);
    };

    List<UUID> getSavedEvents(UUID userId){
        return getSavedEvents(userId);
    };
}
