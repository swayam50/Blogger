package io.wulfcodes.blogger.rest.model.persistent;

import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import static java.time.ZoneOffset.UTC;

@Entity
@Table(name = "posts")
public class Post {

    private Long id;
    private String title;
    private String description;
    private String image;
    private LocalDateTime datetime;
    private Integer userId;

    public Post() {}

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Post id(Long id) {
        this.id = id;
        return this;
    }

    public Post title(String title) {
        this.title = title;
        return this;
    }

    public Post description(String description) {
        this.description = description;
        return this;
    }

    public Post image(String image) {
        this.image = image;
        return this;
    }

    public Post datetime(LocalDateTime datetime) {
        this.datetime = datetime;
        return this;
    }

    public Post userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)    return true;
        if (obj == null)    return false;
        if (this.getClass() != obj.getClass())
            return false;

        Post that = (Post)obj;

        return Objects.equals(this.id, that.id)
                && Objects.equals(this.title, that.title)
                && Objects.equals(this.description, that.description)
                && Objects.equals(this.image, that.image)
                && Objects.equals(this.datetime, that.datetime)
                && Objects.equals(this.userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, datetime, userId);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, title=%s, description=%s, image=%s, datetime=%d, userId=%d]",
                             id.longValue(), title, description, image, datetime.toEpochSecond(UTC), userId.intValue());
    }


}
