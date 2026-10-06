package io.wulfcodes.blogger.rest.model.persistent;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;

@Table(name = "comments")
public class Comment {

    @Id
    @Column("c_id")
    private Long id;

    @Column("c_content")
    private String content;

    @Column("c_publishedOn")
    private LocalDateTime publishedOn;

    @Column("u_id")
    private String userId;

    @Column("p_id")
    private Long postId;

    public Comment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment that = (Comment) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(content, that.content) &&
               Objects.equals(publishedOn, that.publishedOn) &&
               Objects.equals(userId, that.userId) &&
               Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, publishedOn, userId, postId);
    }

    @Override
    public String toString() {
        return String.format("Comment[id=%d, content=%s, publishedOn=%d, userId=%s, postId=%d]",
                id, content, publishedOn != null ? publishedOn.toEpochSecond(UTC) : 0, userId, postId);
    }
}
