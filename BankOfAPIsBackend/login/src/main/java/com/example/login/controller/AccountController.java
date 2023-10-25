package com.example.login.controller;

import com.example.login.dao.AccountDao;
import com.example.login.model.Account;
import com.example.login.request.AccountRequest;
import com.example.login.request.BasicResponse;
import com.example.login.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/getAccounts")
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/getAccounts/{phoneNumber}")
    public List<Account> getAccountByPhoneNumber(@PathVariable String phoneNumber){
        return accountService.getAccountByPhoneNumber(phoneNumber);
    }


    @GetMapping("/getAccounts/{phoneNumber}/{bankName}")
    public List<Account> getAccountByPhoneNumberAndBank(@PathVariable String phoneNumber, @PathVariable String bankName){
        return accountService.getAccountByPhoneNumberAndBank(phoneNumber,bankName);
    }
//    @PutMapping("/updatebalance")
//    public String updateBalance(
//            @RequestParam String bankName,
//            @RequestParam String accountNumber,
//            @RequestParam Double amount,
//            @RequestParam String type
//    ) {
//        AccountRequest accountrequest = new AccountRequest();
//        try{
//            accountrequest.setBankName(bankName);
//            accountrequest.setAccountNumber(accountNumber);
//            accountrequest.setAmount(amount);
//            accountrequest.setType(type);
//            accountService.updateAccount(accountrequest);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return String.valueOf(HttpStatus.OK);
//
//
//
//    }
    @PutMapping("/updatebalance")
    public BasicResponse updateBalance(@RequestBody AccountRequest accountrequest) {
        BasicResponse msg = new BasicResponse();
        try{

            msg = accountService.updateAccount(accountrequest);

        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;



    }
}
