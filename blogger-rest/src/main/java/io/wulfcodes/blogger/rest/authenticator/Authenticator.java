package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.core.UriInfo;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;

public interface Authenticator {

    AuthResponse validateToken(String authToken, UriInfo uriInfo);

    AuthenticationFormat getAuthenticationFormat();

    default boolean isValidToken(String authToken) {
        AuthenticationFormat authenticationFormat = getAuthenticationFormat();
        return authToken.startsWith(authenticationFormat.getScheme());
    }

}
