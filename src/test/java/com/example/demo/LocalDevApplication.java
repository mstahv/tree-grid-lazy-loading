package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

public class LocalDevApplication {
    public static void main(String[] args) {
        SpringApplication.from(DemoApplication::main)
          .with(LocalDevTestcontainersConfig.class)
          .run(args);
    }
}