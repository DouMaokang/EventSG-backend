package eventsg.backend.controller;

import eventsg.backend.model.User;
import eventsg.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping(path = "login/{email}/{password}")
    public UUID login(@PathVariable("email") String emailAddress, @PathVariable("password") String password){
        return userService.login(emailAddress, password);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
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
    public void addInterestedCategory (@PathVariable("id") UUID userId, @PathVariable("category") String category){
        userService.addInterestedCategory(userId, category);
    }

    @DeleteMapping(path = "deleteCategory/{id}/{category}")
    public void deleteInterestedCategory (@PathVariable("id") UUID userId, @PathVariable("category") String category){
        userService.deleteInterestedCategory(userId, category);
    }

    @GetMapping(path = "getCategories/{id}")
    public List<String> getInterestedCategories(@PathVariable("id") UUID userId){
        return userService.getInterestedCategories(userId);
    }

}