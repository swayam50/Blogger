package io.wulfcodes.blogger.rest.authenticator;

import jakarta.ws.rs.core.UriInfo;
import org.springframework.stereotype.Component;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;

import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.PASS_THROUGH;

@Component
public class PassThroughAuthenticator implements Authenticator {

    @Override
    public AuthResponse validateToken(String authToken, UriInfo uriInfo) {
        String passer = authToken.split(" ")[1];

        return !passer.isBlank() && passer.equalsIgnoreCase("true")
               ? AuthResponse.of(OK.getStatusCode(), "Validated successfully.")
               : AuthResponse.of(UNAUTHORIZED.getStatusCode(), "Incorrect pass-through value!");
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return PASS_THROUGH;
    }

}
