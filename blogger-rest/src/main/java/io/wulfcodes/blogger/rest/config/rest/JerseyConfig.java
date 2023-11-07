package io.wulfcodes.blogger.rest.config.rest;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        packages("io.wulfcodes.blogger.rest.route", "io.wulfcodes.blogger.rest.filter", "io.wulfcodes.blogger.rest.provider");
    }

}
