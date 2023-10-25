package com.example.login.controller;


import com.example.login.model.Bank;
import com.example.login.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/getBanks")
    public List<Bank> getBanks(){
        return bankService.getBanks();
    }
}
