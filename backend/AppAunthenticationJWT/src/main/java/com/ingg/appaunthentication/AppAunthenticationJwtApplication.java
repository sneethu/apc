package com.ingg.appaunthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity

public class AppAunthenticationJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAunthenticationJwtApplication.class, args);
	}
}
