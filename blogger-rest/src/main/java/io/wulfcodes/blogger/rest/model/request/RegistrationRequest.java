package io.wulfcodes.blogger.rest.model.request;

import jakarta.ws.rs.FormParam;

public class RegistrationRequest {
    @FormParam("email")
    private String email;

    @FormParam("username")
    private String username;

    @FormParam("password")
    private String password;

    @FormParam("image")
    private String image;

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

    public String getImage() {
        return this.image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
