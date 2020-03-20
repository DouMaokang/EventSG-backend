package eventsg.backend.controller;

import eventsg.backend.model.User;
import eventsg.backend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public void insertUser(User user) {
        userService.insertUser(user);
    }

    @GetMapping
    public Optional<UUID> login(String emailAddress, String password){
        return userService.login(emailAddress, password);
    }

    @GetMapping
    public Optional<User> getUserById(UUID id){
        return userService.getUserById(id);
    }

    @PutMapping
    public void updateUserById(UUID id, User user){
        userService.updateUserById(id, user);
    }

    @DeleteMapping
    public void deleteUserById(UUID id){
        userService.deleteUserById(id);
    }

    @PostMapping
    public void addInterestedCategory (UUID userid, String category){
        userService.addInterestedCategory(userid, category);
    }

    @DeleteMapping
    public void deleteInterestedCategory (UUID userid, String category){
        userService.deleteInterestedCategory(userid, category);
    }

    @GetMapping
    public List<String> getInterestedCategories(UUID userid){
        return userService.getInterestedCategories(userid);
    }

    @PostMapping
    public void saveEvent(UUID userid, UUID eventid){
        userService.saveEvent(userid, eventid);
    };

    @DeleteMapping
    public void unsaveEvent(UUID userid, UUID eventid){
        userService.unsaveEvent(userid, eventid);
    };

    @GetMapping
    public List<UUID> getSavedEvents(UUID userId){
        return userService.getSavedEvents(userId);
    }

}
