package com.buzz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.buzz.controller", "com.buzz.service"})
@EntityScan("com.buzz.model")
@EnableJpaRepositories("com.buzz.repos")
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

}
