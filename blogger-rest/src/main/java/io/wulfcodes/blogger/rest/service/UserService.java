package io.wulfcodes.blogger.rest.service;

import io.wulfcodes.blogger.rest.model.persistent.Image;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.model.request.UserRequest;
import io.wulfcodes.blogger.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String saveUser(UserRequest userRequest) {
        String uuid = UUID.randomUUID().toString();

        User user = new User(uuid, true).email(userRequest.getEmail()).username(userRequest.getUsername()).password(userRequest.getPassword());
        Image image = new Image(uuid, userRequest.getUsername(), userRequest.getEncodedImage());

        int rowsAffected = userRepository.insert(user);
        System.out.println("Rows Affected: " + rowsAffected);
        return uuid;
    }

    public User fetchUser(String id) {
        User user = userRepository.findById(id).orElse(new User().email("HOHOHOHO").password("NKSNDKLSA"));
        return user;
    }

    public User updateUser() {
        return null;
    }

    public void deleteUser() {}

}
