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
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;

@Component
@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN, "http://127.0.0.1:3000");
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_METHODS, "POST");
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Accept, Content-Type");
        responseContext.getHeaders().add(ACCESS_CONTROL_EXPOSE_HEADERS, "Set-Cookie, X-Access-Token, X-Proxy-Token, Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Methods");
    }

}
