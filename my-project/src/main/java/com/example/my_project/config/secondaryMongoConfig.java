package com.example.my_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
    basePackages ="com.example.my_project.secondary",
    mongoTemplateRef ="secondaryMongoTemplate"
)

public class secondaryMongoConfig {
    @Bean
    public MongoTemplate secondaryMongoTemplate(){
        return new MongoTemplate(
            MongoClients.create("mongodb://user2:pass2@localhost:27018/secondarydb"),
            "secondarydb"
        );
    }
}

