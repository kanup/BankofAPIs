package com.example.bankofapi.Repository;

import com.example.bankofapi.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardTransactionRepository extends JpaRepository<Transaction, Long> {
}
