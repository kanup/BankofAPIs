package com.example.login.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userlinkedaccounts_tbl")
public class UserLinkedAccounts {

    @Id
    @GeneratedValue
    private int id;
    private String phoneNumber;
    private String bankName;
    private long accountNumber;
}
