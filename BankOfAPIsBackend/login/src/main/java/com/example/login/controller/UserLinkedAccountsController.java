package com.example.login.controller;

import com.example.login.dao.UserLinkedAccountsDao;
import com.example.login.model.Account;
import com.example.login.model.User;
import com.example.login.model.UserLinkedAccounts;
import com.example.login.service.UserLinkedAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userLinkedAccounts")
public class UserLinkedAccountsController {

    @Autowired
    private UserLinkedAccountsService userLinkedAccountsService;

    @GetMapping("/getUserLinkedAccounts")
    public List<UserLinkedAccounts> getUserLinkedAccounts(){
        return userLinkedAccountsService.getUserLinkedAccounts();
    }

    @GetMapping("/getUserLinkedAccounts/{phoneNumber}")
    public List<UserLinkedAccounts> getUserLinkedAccountsByPhoneNumber(@PathVariable String phoneNumber) {
        return userLinkedAccountsService.getUserLinkedAccountsByPhoneNumber(phoneNumber);
    }

    @PostMapping("/linkAccount")
    public UserLinkedAccounts linkAccount(@RequestBody UserLinkedAccounts u){
        return userLinkedAccountsService.linkAccount(u);
    }
}
