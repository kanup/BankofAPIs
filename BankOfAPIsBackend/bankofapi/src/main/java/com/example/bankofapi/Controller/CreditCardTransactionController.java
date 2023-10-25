package com.example.bankofapi.Controller;

import com.example.bankofapi.Model.Transaction;
import com.example.bankofapi.Service.CreditCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/creditCardTransactions")
public class CreditCardTransactionController {
    @Autowired
    private CreditCardTransactionService transactionService;

    @GetMapping("/getTransactions/{creditCardNumber}")
    public List<Transaction> getTransactionsByCreditCardNumber(@PathVariable String creditCardNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByCreditCardNumber(creditCardNumber);
        return transactions;
    }
}
