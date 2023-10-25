package com.example.login.service;

import com.example.login.dao.UserLinkedAccountsDao;
import com.example.login.model.Account;
import com.example.login.model.UserLinkedAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLinkedAccountsService {

    @Autowired
    private UserLinkedAccountsDao userLinkedAccountsDao;

    public List<UserLinkedAccounts> getUserLinkedAccounts(){
        return (List<UserLinkedAccounts>) userLinkedAccountsDao.findAll();
    }

    public List<UserLinkedAccounts> getUserLinkedAccountsByPhoneNumber( String phoneNumber){
        List<UserLinkedAccounts> userLinkedAccounts = (List<UserLinkedAccounts>) userLinkedAccountsDao.findAll();
        return userLinkedAccounts.stream().filter(u -> u.getPhoneNumber().equals(phoneNumber)).collect(Collectors.toList());
    }

    public UserLinkedAccounts linkAccount( UserLinkedAccounts u){
        return userLinkedAccountsDao.save(u);
    }
}
