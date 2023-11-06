package io.wulfcodes.blogger.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.javatuples.Pair;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Pair<Boolean, Boolean> checkUserExistence(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username)
                             .map(user -> Pair.with(email.equals(user.getEmail()), username.equals(user.getUsername())))
                             .orElseGet(() -> Pair.with(false, false));
    }

    public boolean saveUser(String id, String email, String username, String password) {
        User user = new User(id, true).email(email).username(username).password(password);
        int rowsAffected = userRepository.insert(user);
        return rowsAffected == 1;
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

}
