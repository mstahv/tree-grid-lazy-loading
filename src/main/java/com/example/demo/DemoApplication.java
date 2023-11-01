package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

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
