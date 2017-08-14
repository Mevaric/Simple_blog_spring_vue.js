package ru.ne1ghost.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ne1ghost.blog.config.CustomUserDetails;
import ru.ne1ghost.blog.entities.Role;
import ru.ne1ghost.blog.entities.User;
import ru.ne1ghost.blog.repositories.UserRepository;
import ru.ne1ghost.blog.services.UserService;

import java.util.Arrays;

@SpringBootApplication
public class BlogApplication {


    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
	 */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, ru.ne1ghost.blog.repositories.UserRepository repository, UserService service) throws Exception {
		//Setup a default user if db is empty
		if (repository.count()==0)
            service.save(new User("netghost", "netghost", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));



        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
	 */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

}
