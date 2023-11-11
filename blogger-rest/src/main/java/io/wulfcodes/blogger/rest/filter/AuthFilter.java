package io.wulfcodes.blogger.rest.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;
import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import io.wulfcodes.blogger.rest.provider.annotation.Authenticated;
import io.wulfcodes.blogger.rest.authenticator.Authenticator;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;
import io.wulfcodes.blogger.rest.model.data.AuthData;

import static jakarta.ws.rs.Priorities.AUTHENTICATION;
import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.PROXY_AUTHORIZATION;
import static org.springframework.http.HttpHeaders.WWW_AUTHENTICATE;
import static org.springframework.http.HttpHeaders.PROXY_AUTHENTICATE;

@Provider
@Component
@Authenticated
@Priority(AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpHeaders httpHeaders;

    @Autowired
    private ApplicationContext applicationContext;

    private Authenticated authenticated;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        initialize();

        Authenticator actualAuthenticator = applicationContext.getBean(authenticated.by());
        Authenticator proxyAuthenticator = applicationContext.getBean(authenticated.or());

        String actualToken = httpHeaders.getHeaderString(AUTHORIZATION);
        String proxyToken = httpHeaders.getHeaderString(PROXY_AUTHORIZATION);

        if ((Objects.isNull(actualToken) || actualToken.isBlank()) && (Objects.isNull(proxyToken) || proxyToken.isBlank())) {

            requestContext.abortWith(createResponse(new AuthData("Authorization token is missing!")));

        } else if (actualAuthenticator.isValidToken(actualToken)) {

            AuthData authData = actualAuthenticator.validateToken(requestContext, false);
            if (!authData.isAuthenticated())
                requestContext.abortWith(createResponse(authData));

        } else if (proxyAuthenticator.isValidToken(proxyToken)) {

            AuthData authData = proxyAuthenticator.validateToken(requestContext, true);
            if (!authData.isAuthenticated())
                requestContext.abortWith(createResponse(authData));

        } else {

            requestContext.abortWith(createResponse(new AuthData()));

        }
    }

    private void initialize() {
        Class<?> resourceClass = resourceInfo.getResourceClass();
        Method resourceMethod = resourceInfo.getResourceMethod();

        if (resourceMethod.isAnnotationPresent(Authenticated.class)) {
            authenticated = resourceMethod.getAnnotation(Authenticated.class);
        } else if (resourceClass.isAnnotationPresent(Authenticated.class)) {
            authenticated = resourceClass.getAnnotation(Authenticated.class);
        } else {
            authenticated = null;   // No check required for this condition, as it'll never happen
            // AuthFilter gets triggered only when method or class is annotated with @Authenticated
        }
    }

    private Response createResponse(AuthData authData) {
        return Response.status(authData.obtainStatus())
                       .entity(AuthResponse.of(authData.obtainStatus(), authData.obtainMessage()))
                       .header(WWW_AUTHENTICATE, !authData.isProxy() ? authData.obtainRealm() : null)
                       .header(PROXY_AUTHENTICATE, authData.isProxy() ? authData.obtainRealm() : null)
                       .build();
    }

}
