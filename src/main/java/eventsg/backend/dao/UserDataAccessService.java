package eventsg.backend.dao;

import eventsg.backend.datasource.Assets;
import eventsg.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository("userDao")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a new account into the user table.
     * @param user the new user account
     * @return 0 or 1 (number of rows affected)
     */
    @Override
    public int addUser(User user) {
        String image = Assets.Assets().getUserImage();
        return this.jdbcTemplate.update("INSERT INTO users(userId, userName, firstName, lastName, email, password, " +
                        "birthday, phoneNum, occupation, organization, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                UUID.randomUUID(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getBirthday(), user.getPhoneNum(), user.getOccupation(), user.getOrganization(),
                image
        );
    }

    /**
     * Get all users in the database.
     * @return a list of users.
     */
    @Override
    public List<User> getAllUser() {
        final String sql = "SELECT * FROM users";
        List<User> userList = jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    UUID userId = UUID.fromString(resultSet.getString("userId"));
                    String userName = resultSet.getString("userName");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
//                    String password = resultSet.getString("password");
                    LocalDate birthday = resultSet.getObject("birthday", LocalDate.class);
                    int phoneNum = Integer.parseInt(resultSet.getString("phoneNum"));
                    String occupation = resultSet.getString("occupation");
                    String organization = resultSet.getString("organization");
                    String image = resultSet.getString("image");
                    return new User(userId, userName, firstName, lastName, email, birthday,
                            phoneNum, occupation, organization, image);
                });
        return userList;
    }

    /**
     * Check the Validity of the login information provided. If the email address and the password can be found in the
     * database, it will return the found userId.
     * @param email Login email address
     * @param password Login password
     * @return If successful, returns a UUID, otherwise returns null.
     */
    @Override
    public UUID login(String email, String password) {
        final String sql = "SELECT userId FROM users WHERE LOWER(email) = ? AND password = ? ";

        UUID uuid;
                try {
                    uuid = jdbcTemplate.queryForObject(sql,
                            new Object[]{email, password}, UUID.class);
                } catch (Exception e) {
                    uuid = null;
                }
        return uuid;
    }

    /**
     * Get user by userId
     * @param id userId
     * @return if a matching user is found, return found User; else return null.
     */
    @Override
    public User getUserById(UUID id) {
        final String sql = "SELECT * FROM users WHERE userId = ?";

        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, i) -> {
                        UUID userId = UUID.fromString(resultSet.getString("userId"));
                        String userName = resultSet.getString("userName");
                        String firstName = resultSet.getString("firstName");
                        String lastName = resultSet.getString("lastName");
                        String email = resultSet.getString("email");
//                    String password = resultSet.getString("password");
                        LocalDate birthday = resultSet.getObject("birthday", LocalDate.class);
                        int phoneNum = Integer.parseInt(resultSet.getString("phoneNum"));
                        String occupation = resultSet.getString("occupation");
                        String organization = resultSet.getString("organization");
                        String image = resultSet.getString("image");

                        return new User(userId, userName, firstName, lastName, email, birthday,
                                phoneNum, occupation, organization, image);
                    });
        } catch (Exception e) {
            user = null;
        }
        return user;
    }



    /**
     * Update user account info.
     * @param id userId
     * @param user updated user account.
     * @return TODO need to combine update statements
     */
    @Override
    public int updateUserById(UUID id, User user) {
        this.jdbcTemplate.update("update users set userName = ? where userId = ?", user.getUserName(), id);
        this.jdbcTemplate.update("update users set firstName = ? where userId = ?", user.getFirstName(), id);
        this.jdbcTemplate.update("update users set lastName = ? where userId = ?", user.getLastName(), id);
        this.jdbcTemplate.update("update users set email = ? where userId = ?", user.getEmail(), id);
        this.jdbcTemplate.update("update users set password = ? where userId = ?", user.getPassword(), id);
        this.jdbcTemplate.update("update users set birthday = ? where userId = ?", user.getBirthday(), id);
        this.jdbcTemplate.update("update users set phoneNum = ? where userId = ?", user.getPhoneNum(), id);
        this.jdbcTemplate.update("update users set occupation = ? where userId = ?", user.getOccupation(), id);
        this.jdbcTemplate.update("update users set organization = ? where userId = ?", user.getOrganization(), id);
        return 0;
    }

    /**
     * deletes an account from the user table.
     * @param id userId to be deleted
     * @return 0 or 1. 1 means successful.
     */
    @Override
    public int deleteUserById(UUID id) {
        return jdbcTemplate.update("DELETE FROM users WHERE userId = ?", id);
    }

    /**
     * Add a record of interested category in the user_interested_category table.
     * @param userId UUID
     * @param category interested category
     * @return 0 or 1
     */
    @Override
    public int addInterestedCategory(UUID userId, String category) {
        return jdbcTemplate.update("INSERT INTO user_interested_category(userId, category) VALUES(?,?)", userId, category);
    }

    /**
     * Delete a record of interested category from the database.
     * @param userId UUID
     * @param category category no longer interested in
     * @return 0 or 1
     */
    @Override
    public int deleteInterestedCategory(UUID userId, String category) {
        return jdbcTemplate.update("DELETE FROM user_interested_category WHERE userId = ? AND category = ?", userId, category);
    }

    /**
     * Get a list of categories that user of userid is insterested in.
     * @param userId UUID
     * @return a list of categories
     */
    @Override
    public List<String> getInterestedCategories(UUID userId) {
        final String sql = "SELECT category FROM user_interested_category WHERE userId = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{userId},
                (resultSet, i) -> {
                    String category = resultSet.getString("category");
                    return category;
                });
    }


    /**
     * Add a record of (userId, eventId) into user_saved_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    @Override
    public int saveEvent(UUID userId, UUID eventId) {
        return jdbcTemplate.update("INSERT INTO user_saved_event(userId, eventId) VALUES(?,?)", userId, eventId);
    }


    /**
     * Delete a record of (userId, eventId) from user_saved_event table.
     * @param userId UUID
     * @param eventId UUID
     * @return 0 or 1
     */
    @Override
    public int unsaveEvent(UUID userId, UUID eventId) {
        return jdbcTemplate.update("DELETE FROM user_saved_event WHERE userId = ? AND eventId = ?", userId, eventId);

    }

    /**
     * Get a list of eventIds that are saved by a user.
     * @param userId UUID
     * @return a list of eventIds.
     */
    @Override
    public List<UUID> getSavedEvents(UUID userId) {
        final String sql = "SELECT eventId FROM user_saved_event WHERE userId = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{userId},
                (resultSet, i) -> {
                    UUID eventId  = UUID.fromString(resultSet.getString("eventId"));
                    return eventId;
                });
    }
}