package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class LocalDevTestcontainersConfig {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer mongoDBContainer() {
        return new PostgreSQLContainer("postgres:16.0");
   }

}