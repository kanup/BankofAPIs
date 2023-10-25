package com.example.login.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    int id;
    Long accountNo;
    double amount;
    String bankName;
    String type;

}
