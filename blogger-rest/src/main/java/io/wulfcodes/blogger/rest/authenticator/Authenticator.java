package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.core.Cookie;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;

public interface Authenticator {

    AuthResponse validateToken(String authToken, Cookie cookie);

    AuthenticationFormat getAuthenticationFormat();

    default boolean isValidToken(String authToken) {
        AuthenticationFormat authenticationFormat = getAuthenticationFormat();
        return authToken.startsWith(authenticationFormat.getScheme());
    }

}
