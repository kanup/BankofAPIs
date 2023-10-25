package com.example.banktransaction.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class BankStatement {
    private Date date;
    private String purpose;
    private BigDecimal withdrawal;
    private BigDecimal deposit;
    // Constructors, getters, and setters for the above fields
}
