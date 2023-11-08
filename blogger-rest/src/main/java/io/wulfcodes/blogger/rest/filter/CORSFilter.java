package io.wulfcodes.blogger.rest.filter;

import java.io.IOException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;

@Component
@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN, "http://127.0.0.1:3000");
        containerResponseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Accept, Content-Type");
        containerResponseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        containerResponseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_METHODS, "POST");
    }
}
