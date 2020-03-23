package eventsg.backend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import eventsg.backend.model.User;
import eventsg.backend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(path = "insertUser")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @GetMapping(path = "login/{email}/{password}")
    public UUID login(@PathVariable("email") String emailAddress, @PathVariable("password") String password){
        return userService.login(emailAddress, password)
                .orElse(null);
    }

    @GetMapping(path = "getUser/{id}")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id)
                .orElse(null);
    }

    @PutMapping(path = "updateUser/{id}")
    public void updateUserById(@PathVariable("id")UUID id, @RequestBody User user){
        userService.updateUserById(id, user);
    }

    @DeleteMapping(path = "deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
    }

    @PostMapping(path = "addCategory/{id}/{category}")
    public void addInterestedCategory (@PathVariable("id") UUID userid, @PathVariable("category") String category){
        userService.addInterestedCategory(userid, category);
    }

    @DeleteMapping(path = "deleteCategory/{id}/{category}")
    public void deleteInterestedCategory (@PathVariable("id") UUID userid, @PathVariable("category") String category){
        userService.deleteInterestedCategory(userid, category);
    }

    @GetMapping(path = "getCategories/{id}")
    public List<String> getInterestedCategories(@PathVariable("id") UUID userid){
        return userService.getInterestedCategories(userid);
    }

    @PostMapping(path = "saveEvent/{userId}/{eventId}")
    public void saveEvent(@PathVariable("userId") UUID userid, @PathVariable("eventId") UUID eventid){
        userService.saveEvent(userid, eventid);
    };

    @DeleteMapping(path = "unsaveEvent/{userId}/{eventId}")
    public void unsaveEvent(@PathVariable("userId") UUID userid, @PathVariable("eventId")UUID eventid){
        userService.unsaveEvent(userid, eventid);
    };

    @GetMapping(path = "getSavedEvents/{userId}")
    public List<UUID> getSavedEvents(@PathVariable("userId") UUID userId){
        return userService.getSavedEvents(userId);
    }

}
