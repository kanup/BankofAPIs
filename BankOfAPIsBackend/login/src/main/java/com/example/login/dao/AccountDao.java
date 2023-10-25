package com.example.login.dao;

import com.example.login.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountDao extends CrudRepository<Account, Integer> {
    Optional<Account> findByBankNameAndAccountNo(String bankName, Long accountNo);
}


