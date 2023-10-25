package com.example.login;

import com.example.login.dao.AccountDao;
import com.example.login.model.Account;
import com.example.login.request.AccountRequest;
import com.example.login.request.BasicResponse;
import com.example.login.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao accountDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccounts() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountDao using when/thenReturn
        when(accountDao.findAll()).thenReturn(accounts);

        // Call the service method
        List<Account> response = accountService.getAccounts();

        // Verify the response
        verify(accountDao, times(1)).findAll();
        assertEquals(accounts, response);
    }

    @Test
    public void testGetAccountByPhoneNumberAndBank() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountDao using when/thenReturn
        when(accountDao.findAll()).thenReturn(accounts);

        // Call the service method
        List<Account> response = accountService.getAccountByPhoneNumberAndBank("1234567890", "Sample Bank");

        // Verify the response
        verify(accountDao, times(1)).findAll();
        assertEquals(accounts, response);
    }

    @Test
    public void testGetAccountByPhoneNumber() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountDao using when/thenReturn
        when(accountDao.findAll()).thenReturn(accounts);

        // Call the service method
        List<Account> response = accountService.getAccountByPhoneNumber("1234567890");

        // Verify the response
        verify(accountDao, times(1)).findAll();
        assertEquals(accounts, response);
    }

    @Test
    public void testUpdateAccount() {
        // Create a sample account request
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("Sample Bank");
        accountRequest.setAccountNo(1234567890L);
        accountRequest.setAmount(500.0);
        accountRequest.setType("w");

        // Create a sample existing account
        Account existingAccount = new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890");

        // Mock the behavior of the accountDao using when/thenReturn
        when(accountDao.findByBankNameAndAccountNo("Sample Bank", 1234567890L)).thenReturn(Optional.of(existingAccount));
        when(accountDao.save(existingAccount)).thenReturn(existingAccount);

        // Call the service method
        BasicResponse response = accountService.updateAccount(accountRequest);

        // Verify the response
        verify(accountDao, times(1)).findByBankNameAndAccountNo("Sample Bank", 1234567890L);
        verify(accountDao, times(1)).save(existingAccount);
        assertEquals("success", response.getMessage());
    }
}

