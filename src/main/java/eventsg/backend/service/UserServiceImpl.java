package eventsg.backend.service;

import eventsg.backend.dao.UserDao;
import eventsg.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public int insertUser(User user){
        return userDao.insertUser(user);
    }

    public Optional<UUID> login(String email, String password){
        return userDao.login(email, password);
    }

    public Optional<User> getUserById(UUID id){
        return userDao.getUserById(id);
    }

    public int updateUserById(UUID id, User user){
        return userDao.updateUserById(id, user);
    }

    public int deleteUserById(UUID id){
        return userDao.deleteUserById(id);
    }

    public int addInterestedCategory (UUID userid, String category){
        return userDao.addInterestedCategory(userid, category);
    }

    public int deleteInterestedCategory (UUID userid, String category){
        return userDao.deleteInterestedCategory(userid, category);
    }

    public List<String> getInterestedCategories(UUID userid){
        return userDao.getInterestedCategories(userid);
    }

    public int saveEvent(UUID userid, UUID eventid){
        return userDao.saveEvent(userid, eventid);
    }

    public int unsaveEvent(UUID userid, UUID eventid){
        return userDao.unsaveEvent(userid, eventid);
    }

    public List<UUID> getSavedEvents(UUID userId){
        return userDao.getSavedEvents(userId);
    }
}