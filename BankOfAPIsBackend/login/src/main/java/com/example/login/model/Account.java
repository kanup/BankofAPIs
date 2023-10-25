package com.example.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts_tbl")
@ToString
public class Account {

    @Id
    @GeneratedValue
    private int id;
    private Long accountNo;
    private double balance;
    private String ifscCode;
    private String bankName;
    private String holderName;
    private String phoneNumber;
}
