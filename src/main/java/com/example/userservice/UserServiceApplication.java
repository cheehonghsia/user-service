package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the User Service application.
 * <p>
 * This class serves as the entry point for the Spring Boot application.
 * It is annotated with {@code @SpringBootApplication}, which enables auto-configuration
 * and component scanning.
 *
 * @author Chee Hong Hsia
 * @version 1.0
 */
@SpringBootApplication
public class UserServiceApplication {

	/**
	 * The main method which serves as the entry point for the application.
	 *
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
