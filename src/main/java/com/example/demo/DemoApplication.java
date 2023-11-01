package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	/**
	 * DO NOT USE THIS to run to the demo, unless you setup
	 * the DB connection and the DB schema yourself. For playing
	 * around with the demo, use the LocalDevApplication class
	 * that uses TestContainers to launch a test DB with Docker.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
