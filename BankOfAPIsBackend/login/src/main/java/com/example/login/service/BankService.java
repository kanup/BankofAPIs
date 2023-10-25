package com.example.login.service;

import com.example.login.dao.BankDao;
import com.example.login.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankDao bankDao;

    public List<Bank> getBanks(){
        return (List<Bank>)bankDao.findAll();
    }
}
