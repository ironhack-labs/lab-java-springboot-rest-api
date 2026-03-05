package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main application class for the Spring Boot demo.
 * This class is responsible for bootstrapping the application.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * The entry point of the application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}