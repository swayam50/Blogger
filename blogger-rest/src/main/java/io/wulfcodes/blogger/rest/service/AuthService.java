package io.wulfcodes.blogger.rest.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Quartet;
import io.wulfcodes.blogger.rest.model.persistent.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Quartet<Boolean, Boolean, String, String> registerUser(String email, String username, String password, String profilePic) {
        Pair<Boolean, Boolean> emailExists_usernameExists = userService.checkUserExistence(email, username);
        boolean emailExists = emailExists_usernameExists.getValue0().booleanValue();
        boolean usernameExists = emailExists_usernameExists.getValue1().booleanValue();

        if (emailExists || usernameExists) {
            return Quartet.with(false, true, null, "%s already exists!".formatted(emailExists ? "Email" : "Username"));
        }

        String userId = UUID.randomUUID().toString();

        boolean userSaved = userService.saveUser(userId, email, username, passwordEncoder.encode(password));
        if (!userSaved)
            return Quartet.with(false, false, userId, "User wasn't saved successfully!");

        boolean imageSaved = imageService.saveUserImage(userId, username, profilePic);
        if (!imageSaved)
            return Quartet.with(true, false, userId, "User saved with id '%s' successfully. But profile pic wasn't saved!".formatted(userId));

        return Quartet.with(true, false, userId, "User with id '%s' registered successfully.".formatted(userId));
    }

    public Triplet<Boolean, Boolean, String> loginUser(String username, String password) {
        Optional<User> userOptional = userService.fetchUserByUsername(username);
        return userOptional.map(user -> {
                                    boolean isUserValid = passwordEncoder.matches(password, user.getPassword());
                                    String message = isUserValid ? "User validated successfully." : "Are you sure the password is correct?";
                                    return Triplet.with(true, isUserValid, message);
                                })
                           .orElseGet(() -> Triplet.with(false, false, "User not found!"));

    }

}
