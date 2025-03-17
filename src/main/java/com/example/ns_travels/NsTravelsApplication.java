package com.example.ns_travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.ns_travels.repository")
@EntityScan(basePackages = "com.example.ns_travels.model")
public class NsTravelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NsTravelsApplication.class, args);
    }

}
