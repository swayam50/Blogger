package io.wulfcodes.blogger.rest;

import io.wulfcodes.blogger.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BloggerRESTAppCLI {

    @Bean
    public CommandLineRunner runner() {
        return args -> {};
    }

}
