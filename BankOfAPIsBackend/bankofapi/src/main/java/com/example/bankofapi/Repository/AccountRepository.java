package com.example.bankofapi.Repository;

import com.example.bankofapi.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Find an account by phone number and account number
    Account findByPhoneNumberAndAccountNumber(String phoneNumber, String accountNumber);
}
