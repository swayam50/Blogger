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
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import io.wulfcodes.blogger.rest.provider.annotation.Authenticated;
import io.wulfcodes.blogger.rest.authenticator.Authenticator;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;
import org.springframework.stereotype.Component;

import static jakarta.ws.rs.Priorities.AUTHENTICATION;
import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.HttpHeaders.WWW_AUTHENTICATE;
import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Provider
@Component
@Authenticated
@Priority(AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders httpHeaders;

    @Autowired
    private ApplicationContext applicationContext;

    private Authenticated authenticated;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        initialize();

        Authenticator actualAuthenticator = applicationContext.getBean(authenticated.by());
        Authenticator contingencyAuthenticator = applicationContext.getBean(authenticated.or());

        String authToken = httpHeaders.getHeaderString(AUTHORIZATION);

        if (Objects.isNull(authToken) || authToken.isBlank()) {

            requestContext.abortWith(createGenericResponse(null, "Authorization token is missing!"));

        } else if (actualAuthenticator.isValidToken(authToken)) {

            AuthResponse response = actualAuthenticator.validateToken(authToken, uriInfo);
            if (response.status().intValue() != OK.getStatusCode())
                requestContext.abortWith(createGenericResponse(response, actualAuthenticator.getAuthenticationFormat().getRealm()));

        } else if (contingencyAuthenticator.isValidToken(authToken)) {

            AuthResponse response = contingencyAuthenticator.validateToken(authToken, uriInfo);
            if (response.status().intValue() != OK.getStatusCode())
                requestContext.abortWith(createGenericResponse(response, actualAuthenticator.getAuthenticationFormat().getRealm()));

        } else {

            requestContext.abortWith(createGenericResponse(null, null));

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

    private Response createGenericResponse(AuthResponse response, String message) {
        if (Objects.isNull(response))
            response = AuthResponse.of(UNAUTHORIZED.getStatusCode(), Objects.nonNull(message) && !message.isBlank() ? message : "Invalid authorization token!");

        return Response.status(response.status())
                .entity(response)
                .header(WWW_AUTHENTICATE, message)
                .build();
    }

}
