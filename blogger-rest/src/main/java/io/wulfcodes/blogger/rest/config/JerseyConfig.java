package io.wulfcodes.blogger.rest.config;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        packages("io.wulfcodes.blogger.rest.route", "io.wulfcodes.blogger.rest.provider");
    }

}
