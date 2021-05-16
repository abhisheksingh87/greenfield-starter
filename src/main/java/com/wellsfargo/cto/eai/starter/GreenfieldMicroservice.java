package com.wellsfargo.cto.eai.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GreenfieldMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(GreenfieldMicroservice.class, args);
    }

}
