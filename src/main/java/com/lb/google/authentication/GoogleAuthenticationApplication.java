package com.lb.google.authentication;

import com.lb.google.authentication.config.AuthenticationTokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleAuthenticationApplication {
	@Autowired
	AuthenticationTokenExtractor authenticationTokenExtractor;
	public static void main(String[] args) {
		SpringApplication.run(GoogleAuthenticationApplication.class, args);
	}

}
