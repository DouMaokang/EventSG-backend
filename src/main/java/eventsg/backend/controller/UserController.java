package eventsg.backend.controller;

import eventsg.backend.model.User;
import eventsg.backend.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void insertUser(User user) {
        userService.insertUser(user);
    }

    public Optional<UUID> login(String emailAddress, String password){
        return userService.login(emailAddress, password);
    }

    public Optional<User> getUserById(UUID id){
        return userService.getUserById(id);
    }

    public void updateUserById(UUID id, User user){
        userService.updateUserById(id, user);
    }

    public void deleteUserById(UUID id){
        userService.deleteUserById(id);
    }

    public void addInterestedCategory (UUID userid, String category){
        userService.addInterestedCategory(userid, category);
    }

    public void deleteInterestedCategory (UUID userid, String category){
        userService.deleteInterestedCategory(userid, category);
    }

    public List<String> getInterestedCategories(UUID userid){
        return userService.getInterestedCategories(userid);
    }

    public void saveEvent(UUID userid, UUID eventid){
        userService.saveEvent(userid, eventid);
    };

    public void unsaveEvent(UUID userid, UUID eventid){
        userService.unsaveEvent(userid, eventid);
    };

    public List<UUID> getSavedEvents(UUID userId){
        return userService.getSavedEvents(userId);
    }

}
