package io.wulfcodes.blogger.rest.model.persistent;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
public class User implements Persistable<String> {

    @Id
    @Column("u_id")
    private String id;

    @Column("u_email")
    private String email;

    @Column("u_username")
    private String username;

    @Column("u_password")
    private String password;

    @Transient
    private Boolean isNewUser;

    public User() {}

    public User(String id, Boolean isNewUser) {
        this.id = id;
        this.isNewUser = isNewUser;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isNew() {
        return Objects.nonNull(isNewUser) ? isNewUser.booleanValue() : false;
    }

    public String getId() {
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

    public Boolean getIsNewUser() {return this.isNewUser;}

    public void setId(String id) {
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

    public void setIsNewUser(Boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public User id(String id) {
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

    public User isNewUser(Boolean isNewUser) {
        this.isNewUser = isNewUser;
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
            && Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, email=%s, username=%s, password=%s]",
                             id, email, username, password);
    }
}
