package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.core.UriInfo;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;

import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.JWT;

@Component
public class JWTAuthenticator implements Authenticator {

    @Override
    public AuthResponse validateToken(String authToken, UriInfo uriInfo) {
        return null;
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return JWT;
    }

}
