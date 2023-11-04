package io.wulfcodes.blogger.rest.model.request;

public class UserRequest {
    private String email;
    private String username;
    private String password;
    private String encodedImage;

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
