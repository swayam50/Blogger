package io.wulfcodes.blogger.rest.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.blogger.rest.model.persistent.Image;
import io.wulfcodes.blogger.rest.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public boolean saveUserImage(String userId, String username, String encodedImage) {
        Image image = new Image(userId, username, encodedImage);
        Image savedImage = imageRepository.insert(image);
        return Objects.nonNull(savedImage);
    }

    public boolean savePostImage(Long postId, String title, String encodedImage) {
        Image image = new Image(postId, title, encodedImage);
        Image savedImage = imageRepository.insert(image);
        return Objects.nonNull(savedImage);
    }
}
