package com.example.login.dao;

import com.example.login.model.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankDao extends CrudRepository<Bank, Integer> {
}
