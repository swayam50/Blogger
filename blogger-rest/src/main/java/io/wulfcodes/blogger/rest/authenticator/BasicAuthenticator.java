package io.wulfcodes.blogger.rest.authenticator;

import java.util.Base64;
import java.util.Optional;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.service.UserService;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.BASIC;

@Component
public class BasicAuthenticator implements Authenticator {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse validateToken(String authToken, UriInfo uriInfo) {
        String base64Credentials = authToken.split(" ")[1];
        String[] credentials = new String(Base64.getDecoder().decode(base64Credentials), ISO_8859_1).split(":");
        String username = credentials[0], password = credentials[1];

        Optional<User> userOptional = userService.fetchUserByUsername(username);

        return userOptional.map(user -> {
                               String userId = uriInfo.getPathParameters().getFirst("userId"); // need to make it more generic in-case we use postId as well
                               if (!passwordEncoder.matches(password, user.getPassword()))
                                   return AuthResponse.of(UNAUTHORIZED.getStatusCode(), "Password is incorrect!");
                               else if (!user.getId().equals(userId))
                                   return AuthResponse.of(FORBIDDEN.getStatusCode(), "Not allowed to access this user information!");
                               else
                                   return AuthResponse.of(OK.getStatusCode(), "User validation successful.");
                           })
                           .orElseGet(() -> AuthResponse.of(NOT_FOUND.getStatusCode(), "User not found!"));
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return BASIC;
    }

}
