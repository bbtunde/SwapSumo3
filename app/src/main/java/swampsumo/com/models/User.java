package swampsumo.com.models;

/**
 * Created by babatundedennis on 1/15/17.
 */
public class User {
    private String userID;
    private String userToken;
    private String firstName;
    private String lastName;
    private String profilePic;
    private String email;

    public User(){

    }

    public User(String id, String token, String firstname, String lastname, String pic, String email){
        this.userID = id;
        this.userToken = token;
        this.firstName = firstname;
        this.lastName = lastname;
        this.profilePic = pic;
        this.email = email;
    }

    public User(String token, String firstname, String lastname, String pic, String email){
        this.userToken = token;
        this.firstName = firstname;
        this.lastName = lastname;
        this.profilePic = pic;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

