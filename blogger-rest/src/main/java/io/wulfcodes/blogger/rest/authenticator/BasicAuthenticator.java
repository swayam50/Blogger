package io.wulfcodes.blogger.rest.authenticator;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.exception.ValidationException;
import io.wulfcodes.blogger.rest.service.UserService;
import io.wulfcodes.blogger.rest.model.data.AuthData;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.BASIC;

@Component
public class BasicAuthenticator implements Authenticator {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthData validateToken(ContainerRequestContext requestContext, boolean proxy) throws ValidationException {
        String base64Credentials = getTokenData(requestContext, proxy).getCredentials();
        String[] credentials = new String(Base64.getDecoder().decode(base64Credentials), ISO_8859_1).split(":");
        String username = credentials[0], password = credentials[1];

        Optional<User> userOptional = userService.fetchUserByUsername(username);

        return userOptional.map(user -> {
                               String userId = requestContext.getUriInfo().getPathParameters().getFirst("userId");
                               String postId = requestContext.getUriInfo().getPathParameters().getFirst("postId");

                               if (!passwordEncoder.matches(password, user.getPassword()))
                                   return new AuthData("Invalid credentials!", proxy, getAuthenticationFormat().getRealm());
                               else if (Objects.nonNull(userId) && !user.getId().equals(userId))
                                   return new AuthData(FORBIDDEN.getStatusCode(), "Not allowed to access this user's information!", proxy, getAuthenticationFormat().getRealm());
                               else if (Objects.nonNull(postId)) // TODO: Add another && condition to check postId is valid or not, get postId from List<Post> aggregate whatever used in User Model
                                   return new AuthData(FORBIDDEN.getStatusCode(), "Not allowed to access this post's information!", proxy, getAuthenticationFormat().getRealm());
                               else
                                   return new AuthData(true, proxy, getAuthenticationFormat().getRealm());
                           })
                           .orElseGet(() -> new AuthData(NOT_FOUND.getStatusCode(), "User not found!", proxy, getAuthenticationFormat().getRealm()));
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return BASIC;
    }

}
