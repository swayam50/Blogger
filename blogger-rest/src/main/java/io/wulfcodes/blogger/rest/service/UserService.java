package io.wulfcodes.blogger.rest.service;

import java.util.UUID;
import io.wulfcodes.blogger.rest.model.persistent.Image;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.javatuples.Pair;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String saveUser(String email, String username, String password, String profilePic) {
        String uuid = UUID.randomUUID().toString();

        User user = new User(uuid, true).email(email).username(username).password(password);
        Image image = new Image(uuid, username, profilePic);

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

    public void deleteUser() {
    }

    public Pair<Boolean, Boolean> checkUserExistence(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username)
                             .map(user -> Pair.with(email.equals(user.getEmail()), username.equals(user.getUsername())))
                             .orElseGet(() -> Pair.with(false, false));
    }

}
