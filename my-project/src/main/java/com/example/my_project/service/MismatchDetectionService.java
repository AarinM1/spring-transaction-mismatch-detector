package com.example.my_project.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.my_project.config.primaryMongoConfig;
import com.example.my_project.config.secondaryMongoConfig;
import com.example.my_project.model.BankTransaction;

@Service
public class MismatchDetectionService{
    @Autowired
    private primaryMongoConfig primaryRepo;
    @Autowired
    private secondaryMongoConfig secondaryRepo;
    @Autowired
    private ServiceNowClient serviceNowClient;
    @Async
    @Scheduled(cron = "0 0 0 * * MON") // run every Monday at midnight
    
    public void compareDatabases(){
        List<BankTransaction> primaryTransactions = primaryRepo.findAll();
        List<BankTransaction> secondaryTransactions = secondaryRepo.findAll();
    }
}