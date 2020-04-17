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

    /**
     * Adds a new user.
     * @param user the user object
     */
    @PostMapping(path = "add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    /**
     * Performs user login authentication.
     * @param emailAddress the email address of the user
     * @param password the password
     * @return the id of the user if authentication succeeded
     */
    @GetMapping(path = "login/{email}/{password}")
    public UUID login(@PathVariable("email") String emailAddress, @PathVariable("password") String password){
        return userService.login(emailAddress, password);
    }

    /**
     * Returns a user object.
     * @param id the id of the user to be returned
     * @return the user object
     */
    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id);
    }

    /**
     * Updates user information.
     * @param id the id of the user to be updated
     * @param user the new user object
     */
    @PutMapping(path = "updateUser/{id}")
    public void updateUserById(@PathVariable("id")UUID id, @RequestBody User user){
        userService.updateUserById(id, user);
    }

    /**
     * Deletes a user.
     * @param id the id of the user to be deleted
     */
    @DeleteMapping(path = "deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
    }

    /**
     * Adds interested categories into a user account.
     * @param userId the id of the user
     * @param category the interested category
     */
    @PostMapping(path = "addCategory/{id}/{category}")
    public void addInterestedCategory (@PathVariable("id") UUID userId, @PathVariable("category") String category){
        userService.addInterestedCategory(userId, category);
    }

    /**
     * Removes a interested category from a user account.
     * @param userId the id of the user
     * @param category the category to be removed
     */
    @DeleteMapping(path = "deleteCategory/{id}/{category}")
    public void deleteInterestedCategory (@PathVariable("id") UUID userId, @PathVariable("category") String category){
        userService.deleteInterestedCategory(userId, category);
    }

    /**
     * Returns a list of interested categories of a user.
     * @param userId the id of the user
     * @return a list of interested categories of a user
     */
    @GetMapping(path = "getCategories/{id}")
    public List<String> getInterestedCategories(@PathVariable("id") UUID userId){
        return userService.getInterestedCategories(userId);
    }

}