package io.wulfcodes.blogger.rest.model.persistent;

import java.sql.Blob;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private Blob image;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public Blob getImage() {
        return this.image;
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

    public void setImage(Blob image) {
        this.image = image;
    }

    public User id(Integer id) {
        this.id = id;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User image(Blob image) {
        this.image = image;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)    return true;
        if (obj == null)    return false;
        if (this.getClass() != obj.getClass())
            return false;

        User that = (User)obj;

        return Objects.equals(this.id, that.id)
            && Objects.equals(this.email, that.email)
            && Objects.equals(this.username, that.username)
            && Objects.equals(this.password, that.password)
            && Objects.equals(this.image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, image);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, email=%s, username=%s, password=%s, image=%s]",
                             id.intValue(), email, username, password, image.toString());
    }
}
