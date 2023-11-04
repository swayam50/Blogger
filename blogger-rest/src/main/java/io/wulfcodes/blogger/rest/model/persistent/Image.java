package io.wulfcodes.blogger.rest.model.persistent;

import io.wulfcodes.blogger.rest.model.value.ImageType;

import static io.wulfcodes.blogger.rest.model.value.ImageType.USER;
import static io.wulfcodes.blogger.rest.model.value.ImageType.POST;

public class Image {
    private String userId;
    private String username;
    private Long postId;
    private String title;
    private ImageType imageType;
    private String encodedImage;

    public Image() {}

    public Image(String userId, String username, String encodedImage) {
        this.imageType = USER;
        this.userId = userId;
        this.username = username;
        this.encodedImage = encodedImage;
    }

    public Image(Long postId, String title, String encodedImage) {
        this.imageType = POST;
        this.postId = postId;
        this.title = title;
        this.encodedImage = encodedImage;
    }
}
