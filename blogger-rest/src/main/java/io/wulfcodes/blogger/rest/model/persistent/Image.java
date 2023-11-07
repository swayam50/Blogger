package io.wulfcodes.blogger.rest.model.persistent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import io.wulfcodes.blogger.rest.model.value.ImageType;

import static io.wulfcodes.blogger.rest.model.value.ImageType.USER;
import static io.wulfcodes.blogger.rest.model.value.ImageType.POST;

@Document(collection = "images")
public class Image {
    @Id
    @Field("imageId")
    private String id;

    @Field("imageType")
    private ImageType imageType;

    @Field("imageEncoded")
    private String imageEncoded;

    @Field("userId")
    private String userId;

    @Field("userUsername")
    private String username;

    @Field("postId")
    private Long postId;

    @Field("postTitle")
    private String title;

    public Image() {}

    public Image(String userId, String username, String imageEncoded) {
        this.imageType = USER;
        this.userId = userId;
        this.username = username;
        this.imageEncoded = imageEncoded;
    }

    public Image(Long postId, String title, String imageEncoded) {
        this.imageType = POST;
        this.postId = postId;
        this.title = title;
        this.imageEncoded = imageEncoded;
    }

    public String getId() {
        return this.id;
    }

    public ImageType getImageType() {
        return this.imageType;
    }

    public String getImageEncoded() {
        return this.imageEncoded;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getPostId() {
        return this.postId;
    }

    public String getTitle() {
        return this.title;
    }
}
