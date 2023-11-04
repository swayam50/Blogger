package io.wulfcodes.blogger.rest.model.persistent;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;

public class Post {

    private Long id;
    private String title;
    private String description;
    private String image;
    private LocalDateTime publishedOn;
    private String userId;

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

    public LocalDateTime getPublishedOn() {
        return this.publishedOn;
    }

    public String getUserId() {
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

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public void setUserId(String userId) {
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

    public Post publishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
        return this;
    }

    public Post userId(String userId) {
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
                && Objects.equals(this.publishedOn, that.publishedOn)
                && Objects.equals(this.userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, publishedOn, userId);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, title=%s, description=%s, image=%s, publishedOn=%d, userId=%d]",
                             id.longValue(), title, description, image, publishedOn.toEpochSecond(UTC), userId);
    }


}
