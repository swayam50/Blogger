package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.container.ContainerRequestContext;
import io.wulfcodes.blogger.rest.exception.ValidationException;
import io.wulfcodes.blogger.rest.model.data.TokenData;
import io.wulfcodes.blogger.rest.model.data.AuthData;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.PROXY_AUTHORIZATION;

public interface Authenticator {

    AuthData validateToken(ContainerRequestContext requestContext, boolean proxy) throws ValidationException;

    AuthenticationFormat getAuthenticationFormat();

    default TokenData getTokenData(ContainerRequestContext requestContext, boolean proxy) {
        String authToken = requestContext.getHeaderString(!proxy ? AUTHORIZATION : PROXY_AUTHORIZATION);
        return TokenData.fetchTokenData(authToken);
    }

    default boolean isValidToken(String authToken) {
        AuthenticationFormat authenticationFormat = getAuthenticationFormat();
        return authToken.startsWith(authenticationFormat.getScheme());
    }

}
