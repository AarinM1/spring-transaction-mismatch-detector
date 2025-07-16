package com.example.my_project.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "transactions")
public class BankTransaction {
    @Id
    private String transactionId;
    private String accountId;
    private double amount;
    private String currency;
    private Instant timestamp;
    private String type; 
    private String source;
    
}
