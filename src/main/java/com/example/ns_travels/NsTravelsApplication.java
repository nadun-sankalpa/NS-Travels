package com.example.ns_travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.ns_travels.entity")
@EnableJpaRepositories("com.example.ns_travels.repository")
public class NsTravelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NsTravelsApplication.class, args);
    }

}
