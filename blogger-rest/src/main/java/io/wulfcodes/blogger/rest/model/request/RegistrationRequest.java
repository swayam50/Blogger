package io.wulfcodes.blogger.rest.model.request;

import jakarta.ws.rs.FormParam;

public class RegistrationRequest {
    @FormParam("email")
    private String email;

    @FormParam("username")
    private String username;

    @FormParam("password")
    private String password;

    @FormParam("profilePic")
    private String profilePic;

    public RegistrationRequest() {}

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
