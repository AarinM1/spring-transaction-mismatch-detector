package com.example.my_project.repository.secondary;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.my_project.model.BankTransaction;
public interface SecondaryRepo extends MongoRepository<BankTransaction, String> {}


