package com.example.bankofapi.Service;

import com.example.bankofapi.Model.Transaction;
import com.example.bankofapi.Repository.CreditCardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardTransactionService {

    @Autowired
    private CreditCardTransactionRepository transactionRepository;
    public List<Transaction> getTransactionsByCreditCardNumber(String creditCardNumber) {
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        transactions = transactions.stream().filter(account -> account.getCreditCardNumber().trim().equals(creditCardNumber.trim())).collect(Collectors.toList());
        return transactions;

    }
}
