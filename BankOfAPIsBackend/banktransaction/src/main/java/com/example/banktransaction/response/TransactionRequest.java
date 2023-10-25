package com.example.banktransaction.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter

public class TransactionRequest {

    private String senderBank;


    private String id;


    private String senderAccountNumber;

    private String beneficiaryAccountNumber;

    private String beneficiaryBank;
    private String ifscCode;
    private BigDecimal amount;
    private String purpose;






}

