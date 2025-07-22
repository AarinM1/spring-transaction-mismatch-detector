package com.example.my_project.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.my_project.model.BankTransaction;
import com.example.my_project.repository.primary.PrimaryRepo;
import com.example.my_project.repository.secondary.SecondaryRepo;

@Service
public class MismatchDetectionService {
    @Autowired
    private PrimaryRepo primaryRepo;
    @Autowired
    private SecondaryRepo secondaryRepo;
    @Autowired
    private ServiceNowClient serviceNowClient;

    @Async
    @Scheduled(cron = "0 0 0 * * MON") // run every Monday at midnight

    public void compareDatabases() {
        List<BankTransaction> primaryTransactions = primaryRepo.findAll();
        List<BankTransaction> secondaryTransactions = secondaryRepo.findAll();
        Map<String, BankTransaction> primaryMap = primaryTransactions.stream()
                .collect(Collectors.toMap(BankTransaction::getTransactionId, Function.identity()));
        Map<String, BankTransaction> secondaryMap = secondaryTransactions.stream()
                .collect(Collectors.toMap(BankTransaction::getTransactionId, Function.identity()));

        Set<String> allIds = new HashSet<>();
        allIds.addAll(primaryMap.keySet());
        allIds.addAll(secondaryMap.keySet());
        for (String id : allIds) {
            BankTransaction tx1 = primaryMap.get(id);
            BankTransaction tx2 = secondaryMap.get(id);

            if (tx1 == null || tx2 == null) {
                serviceNowClient.createIncident(id, "Missing transaction in one of the databases",
                        Instant.now());
            }

            else if (!tx1.equals(tx2)) {
                serviceNowClient.createIncident(id, "Field mismatch",
                        Instant.now());
            }

        }
    }
}
