package com.example.bankofapi.Service;

import com.example.bankofapi.Model.Account;
import com.example.bankofapi.Model.CreditCard;
import com.example.bankofapi.Model.Transaction;
import com.example.bankofapi.Repository.AccountRepository;
import com.example.bankofapi.Repository.CreditCardRepository;
import com.example.bankofapi.Repository.CreditCardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

// PaymentService.java
@Service
public class PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public boolean makePayment(String phoneNumber, String creditCardNumber, String accountNumber, double amount) {
        // Verify that the account number belongs to the provided phone number
        Account account = accountRepository.findByPhoneNumberAndAccountNumber(phoneNumber, accountNumber);

        if (account != null && account.getBalance() >= amount) {
            // Update the account balance
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);

            // Create a new credit card transaction
            Transaction transaction = new Transaction();
            transaction.setDate(new Date());
            transaction.setCreditCardNumber(creditCardNumber);
            transaction.setTransactionDescription("Credit Card Payment");
            transaction.setTransactionType("Credit");
            transaction.setAmountInRupees(amount);

            creditCardTransactionRepository.save(transaction);

            // Update the credit card's total unbilled transaction
            CreditCard creditCard = creditCardRepository.findByCreditCardNumber(creditCardNumber);
            creditCard.setTotalUnbilledDebits(creditCard.getTotalUnbilledDebits() - amount);
            creditCard.setAvailableCredit(creditCard.getAvailableCredit() + amount);
            creditCardRepository.save(creditCard);

            return true; // Payment successful
        }
        return false; // Payment failed (account not found or insufficient balance)
    }
}
