package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
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


    public User(@JsonProperty("userId") UUID userId,
                @JsonProperty("userName") String userName,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("birthday") LocalDate birthday,
                @JsonProperty("phoneNum") int phoneNum,
                @JsonProperty("occupation") String occupation,
                @JsonProperty("organization") String organization) {
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