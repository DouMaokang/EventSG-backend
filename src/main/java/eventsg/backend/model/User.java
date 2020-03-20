package eventsg.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a user account.
 */
public class User {

    /**
     * Unique user ID assigned to user when account is created.
     */
    private final UUID userId;

    /**
     * Username set by user which should be unique.
     */
    private String userName;

    /**
     * First name of user.
     */
    private String firstName;

    /**
     * Last name of user.
     */
    private String lastName;

    /**
     * Email address of the account which should be unique.
     */
    private String email;

    /**
     * password set by user.
     */
    private String password;

    /**
     * Date of birth of user.
     */
    private LocalDate birthday;

    /**
     * Phone number of user which should be unique.
     */
    private int phoneNum;

    /**
     * Occupation of user.
     */
    private String occupation;

    /**
     * Organization the user belongs to.
     */
    private String organization;
//    private List<String> interestedEventCategories; // separate table needed: userid vs category
//    private List<UUID> savedEvents; // separate table needed: userid vs event id


    public User(UUID userId, String userName, String firstName, String lastName, String email, String password,
                LocalDate birthday, int phoneNum, String occupation, String organization) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.occupation = occupation;
        this.organization = organization;
//        this.interestedEventCategories = new ArrayList<>();
//        this.savedEvents = new ArrayList<>();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getOrganization() {
        return organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
