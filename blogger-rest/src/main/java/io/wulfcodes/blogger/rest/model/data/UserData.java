package io.wulfcodes.blogger.rest.model.data;

public class UserData {
    private String email;
    private String username;
    private String password;
    private String profilePic;

    public UserData() {}

    public UserData(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserData(String username, String profilePic) {
        this.username = username;
        this.profilePic = profilePic;
    }


    public UserData(String email, String username, String password, String profilePic) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getProfilePic() {
        return this.profilePic;
    }
}
