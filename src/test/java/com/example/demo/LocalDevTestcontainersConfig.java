package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@TestConfiguration(proxyBeanMethods = false)
class LocalDevTestcontainersConfig {

    @Bean
    @ServiceConnection
    public OracleContainer serviceConnection() {
        OracleContainer oracle = new OracleContainer("gvenzl/oracle-xe:21-slim")
                .withUsername("testUser")
                .withPassword("testPassword")
                .withReuse(true);

        oracle.start();

        try {
            String dataFileName = "init.sql";
            oracle.copyFileToContainer(MountableFile.forClasspathResource(dataFileName),
                    "/" + dataFileName);
            String[] command = {"sqlplus", "-s", oracle.getUsername() +
                    "/" + oracle.getPassword() + "@//localhost:1521/" + oracle.getDatabaseName(),
                    "@/" + dataFileName
            };
            var execResult = oracle.execInContainer(command);
            System.out.println("execResult is : " + execResult.getStdout());
            System.out.println("execResult error is : " + execResult.getStderr());
            System.out.println("execResult exit code is : " + execResult.getExitCode());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return oracle;
   }

}