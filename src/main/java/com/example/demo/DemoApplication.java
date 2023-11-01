package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

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

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		jdbcTemplate.execute(new String(getClass().getResourceAsStream("/init.sql").readAllBytes(), StandardCharsets.UTF_8));
	}
}
