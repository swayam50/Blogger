package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.container.ContainerRequestContext;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.exception.ValidationException;
import io.wulfcodes.blogger.rest.model.data.AuthData;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.JWT;

@Component
public class JWTAuthenticator implements Authenticator {

    @Override
    public AuthData validateToken(ContainerRequestContext requestContext, boolean proxy) throws ValidationException {
        // TODO: write logic for jwt authenticator
        return new AuthData();
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return JWT;
    }

}
