package eventsg.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final UUID userId;
    private String userName;
    private String email;
    private String password;
    private LocalDate birthday;
    private int phoneNum;
    private String occupation;
    private String organization;
//    private List<String> interestedEventCategories; // separate table needed: userid vs category
//    private List<UUID> savedEvents; // separate table needed: userid vs event id

    public User(UUID userId, String userName, String email, String password, LocalDate birthday, int phoneNum,
                String occupation, String organization) {
        this.userId = userId;
        this.userName = userName;
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
}
