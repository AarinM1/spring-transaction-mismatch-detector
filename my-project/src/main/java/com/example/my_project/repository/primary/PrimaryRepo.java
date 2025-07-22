package com.example.my_project.repository.primary;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.my_project.model.BankTransaction;

public interface PrimaryRepo extends MongoRepository<BankTransaction, String> {}


