package com.example.bankofapi.Dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String phoneNumber;
    private String creditCardNumber;
    private String accountNumber;
    private double amount;

    // Getters and setters
}
