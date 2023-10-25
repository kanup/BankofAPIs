package com.example.banktransaction.service;

import com.example.banktransaction.response.BasicResponseMsg;
import com.example.banktransaction.response.TransactionRequest;



public interface TransactionService {
    BasicResponseMsg saveTransaction(TransactionRequest transactionRequest);

    BasicResponseMsg getTransaction(TransactionRequest transactionRequest);
}
