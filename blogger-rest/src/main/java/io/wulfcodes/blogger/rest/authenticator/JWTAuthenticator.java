package io.wulfcodes.blogger.rest.authenticator;

import java.util.Map;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.Claim;
import io.wulfcodes.blogger.rest.exception.ValidationException;
import io.wulfcodes.blogger.rest.tokenizer.JWTTokenizer;
import io.wulfcodes.blogger.rest.model.data.AuthData;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

import static io.wulfcodes.blogger.rest.model.value.AuthenticationFormat.JWT;

@Component
public class JWTAuthenticator implements Authenticator {

    @Autowired
    private JWTTokenizer tokenizer;

    @Override
    public AuthData validateToken(ContainerRequestContext requestContext, boolean proxy) throws ValidationException {
        String encodedCredentials = getTokenData(requestContext, proxy).getCredentials();
        Map<String, Claim> claims = tokenizer.verifyToken(encodedCredentials);

        return new AuthData();
    }

    @Override
    public AuthenticationFormat getAuthenticationFormat() {
        return JWT;
    }

}
