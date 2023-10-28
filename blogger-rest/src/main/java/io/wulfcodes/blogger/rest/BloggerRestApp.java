package io.wulfcodes.blogger.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggerRestApp {

	public static void main(String[] args) {
		var context = SpringApplication.run(BloggerRestApp.class, args);
	}

}
