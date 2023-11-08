package io.wulfcodes.blogger.rest.model.request;

import jakarta.ws.rs.FormParam;

public class LoginRequest {

    @FormParam("username")
    private String username;

    @FormParam("password")
    private String password;

    public LoginRequest() {}

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
