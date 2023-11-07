package io.wulfcodes.blogger.rest.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.javatuples.Pair;
import org.javatuples.Triplet;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Triplet<Boolean, Boolean, String> registerUser(String email, String username, String password, String profilePic) {
        Pair<Boolean, Boolean> email_username = userService.checkUserExistence(email, username);
        boolean emailExists = email_username.getValue0().booleanValue();
        boolean usernameExists = email_username.getValue1().booleanValue();

        if (emailExists || usernameExists) {
            return Triplet.with(false, true, "%s already exists!".formatted(emailExists ? "Email" : "Username"));
        }

        String userId = UUID.randomUUID().toString();

        boolean userSaved = userService.saveUser(userId, email, username, passwordEncoder.encode(password));
        if (!userSaved)
            return Triplet.with(false, false, "User wasn't saved successfully!");

        boolean imageSaved = imageService.saveUserImage(userId, username, profilePic);
        if (!imageSaved)
            return Triplet.with(true, false, "User saved with id '%s' successfully. But profile pic wasn't saved!".formatted(userId));

        return Triplet.with(true, false, "User with id '%s' registered successfully.".formatted(userId));
    }

}
