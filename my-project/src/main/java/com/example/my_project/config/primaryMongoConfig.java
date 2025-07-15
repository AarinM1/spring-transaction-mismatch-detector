package com.example.my_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.example.my_project.primary",
    mongoTemplateRef= "primaryMongoTemplate"
)


public class primaryMongoConfig{
    @Bean
    @Primary
    public MongoTemplate primaryMongoTemplate(){
        return new MongoTemplate(
            MongoClients.create("mongodb://user1:password1@localhost:27017/primarydb"),
            "primarydb"
        );
    }
}
