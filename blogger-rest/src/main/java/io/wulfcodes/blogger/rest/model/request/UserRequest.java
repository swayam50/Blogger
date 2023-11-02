package io.wulfcodes.blogger.rest.model.request;

import io.wulfcodes.blogger.rest.model.persistent.User;

public class UserRequest {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String encodedImage;

    public Integer getId() {
        return this.id;
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

    public String getEncodedImage() {
        return this.encodedImage;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

}
