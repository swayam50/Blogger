package io.wulfcodes.blogger.rest;

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
