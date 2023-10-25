package com.example.banktransaction.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getSenderBank() {
        return senderBank;
    }

    public void setSenderBank(String senderBank) {
        this.senderBank = senderBank;
    }

    @Column(name="sender_acc_numb")
    private String senderAccountNumber;

    @Column(name = "sender_bank")
    private  String senderBank;
    @Column(name="beneficiary_acc_numb")
    private String beneficiaryAccountNumber;
    @Column(name="beneficiary_bank")
    private String beneficiaryBank;
    private String ifscCode;
    private BigDecimal amount;
    private String purpose;
    @CreationTimestamp
    private LocalDateTime timestamp;


}
