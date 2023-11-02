package io.wulfcodes.blogger.rest;

import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class BloggerRESTApp implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		var context = SpringApplication.run(BloggerRESTApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Add User?");
		User user = new User("dumbo123", "dumbo456").email("temp@gmail.com");
		userRepository.save(user);
	}
}
