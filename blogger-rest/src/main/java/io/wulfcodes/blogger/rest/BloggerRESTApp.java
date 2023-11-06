package io.wulfcodes.blogger.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
	exclude = {
		DataSourceAutoConfiguration.class,
		MongoAutoConfiguration.class,
		SecurityAutoConfiguration.class
	}
)
public class BloggerRESTApp {
	public static void main(String[] args) {
		var context = SpringApplication.run(BloggerRESTApp.class, args);
	}
}
