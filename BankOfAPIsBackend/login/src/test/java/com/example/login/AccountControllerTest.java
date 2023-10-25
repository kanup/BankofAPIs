package com.example.login;

import com.example.login.controller.AccountController;
import com.example.login.dao.AccountDao;
import com.example.login.model.Account;
import com.example.login.request.AccountRequest;
import com.example.login.service.AccountService;
import com.example.login.request.BasicResponse;
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

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccounts() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountService using when/thenReturn
        when(accountService.getAccounts()).thenReturn(accounts);

        // Call the controller method
        List<Account> response = accountController.getAccounts();

        // Verify the response
        verify(accountService, times(1)).getAccounts();
        assertEquals(accounts, response);
    }

    @Test
    public void testGetAccountByPhoneNumber() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountService using when/thenReturn
        when(accountService.getAccountByPhoneNumber("1234567890")).thenReturn(accounts);

        // Call the controller method
        List<Account> response = accountController.getAccountByPhoneNumber("1234567890");

        // Verify the response
        verify(accountService, times(1)).getAccountByPhoneNumber("1234567890");
        assertEquals(accounts, response);
    }

    @Test
    public void testGetAccountByPhoneNumberAndBank() {
        // Create a sample list of accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1234567890L, 1000.0, "IFSC123", "Sample Bank", "John Doe", "1234567890"));

        // Mock the behavior of the accountService using when/thenReturn
        when(accountService.getAccountByPhoneNumberAndBank("1234567890", "Sample Bank")).thenReturn(accounts);

        // Call the controller method
        List<Account> response = accountController.getAccountByPhoneNumberAndBank("1234567890", "Sample Bank");

        // Verify the response
        verify(accountService, times(1)).getAccountByPhoneNumberAndBank("1234567890", "Sample Bank");
        assertEquals(accounts, response);
    }
}

