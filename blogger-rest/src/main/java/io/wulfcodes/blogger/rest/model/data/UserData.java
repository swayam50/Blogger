package io.wulfcodes.blogger.rest.model.data;

public class UserData {
    private String email;
    private String username;
    private String profilePic;

    public UserData() {}

    public UserData(String email, String username, String profilePic) {
        this.email = email;
        this.username = username;
        this.profilePic = profilePic;
    }
}
