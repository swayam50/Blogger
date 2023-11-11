package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.container.ContainerRequestContext;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.exception.ValidationException;
import io.wulfcodes.blogger.rest.model.data.AuthData;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.PASS_THROUGH;

@Component
public class PassThroughAuthenticator implements Authenticator {

    @Override
    public AuthData validateToken(ContainerRequestContext requestContext, boolean proxy) throws ValidationException {
        String passer = getTokenData(requestContext, proxy).getCredentials();

        return passer.equalsIgnoreCase("true")
               ? new AuthData(true, proxy, getAuthenticationFormat().getRealm())
               : new AuthData("Incorrect passer value!", proxy, getAuthenticationFormat().getRealm());
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return PASS_THROUGH;
    }

}
