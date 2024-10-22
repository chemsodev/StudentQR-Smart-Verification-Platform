package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.repository")
public class verification_system_Application {

    public static void main(String[] args) {

        SpringApplication.run(verification_system_Application.class, args);
    }

}

