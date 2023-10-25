package com.example.banktransaction;

import com.example.banktransaction.dao.TransactionDao;
import com.example.banktransaction.model.Transaction;
import com.example.banktransaction.response.BasicResponseMsg;
import com.example.banktransaction.response.TransactionRequest;
import com.example.banktransaction.serviceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionDao transactionDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTransaction() {
        // Create a sample TransactionRequest
        TransactionRequest request = new TransactionRequest();
        request.setSenderAccountNumber("123456789");
        request.setBeneficiaryBank("Sample Bank");
        request.setBeneficiaryAccountNumber("987654321");
        request.setAmount(BigDecimal.valueOf(1000));
        request.setIfscCode("IFSC123");
        request.setSenderBank("My Bank");
        request.setPurpose("Payment");

        // Create a sample Transaction
        Transaction transaction = new Transaction();
        when(transactionDao.save(any())).thenReturn(transaction);

        // Call the service method
        BasicResponseMsg response = transactionService.saveTransaction(request);

        // Verify the response and DAO interaction
        assertEquals("Transaction saved", response.getMessage());
        verify(transactionDao).save(any());
    }

    @Test
    public void testGetTransaction() {
        // Create a sample TransactionRequest
        TransactionRequest request = new TransactionRequest();
        request.setSenderBank("Sample Bank");
        request.setSenderAccountNumber("123456789");

        // Create sample transactions for beneficiary and sender
        Transaction transaction1 = createTransaction("Sample Bank", "123456789", "987654321");
        Transaction transaction2 = createTransaction("Sample Bank", "123456789", "987654322");

        // Mock the behavior of the transactionDao's find methods
        when(transactionDao.findByBeneficiaryBankAndBeneficiaryAccountNumberOrderByTimestampDesc(
                eq("Sample Bank"), eq("123456789"))).thenReturn(List.of(transaction1, transaction2));
        when(transactionDao.findBySenderBankAndSenderAccountNumberOrderByTimestampDesc(
                eq("Sample Bank"), eq("123456789"))).thenReturn(List.of(transaction1, transaction2));

        // Call the service method
        BasicResponseMsg response = transactionService.getTransaction(request);

        // Verify the response and DAO interactions
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Bank statements found", response.getMessage());
        assertNotNull(response.getData());
        assertEquals(4, ((List<?>) response.getData()).size());
    }

    private Transaction createTransaction(String senderBank, String senderAccount, String beneficiaryAccount) {
        Transaction transaction = new Transaction();
        transaction.setSenderBank(senderBank);
        transaction.setSenderAccountNumber(senderAccount);
        transaction.setBeneficiaryBank("Beneficiary Bank");
        transaction.setBeneficiaryAccountNumber(beneficiaryAccount);
        transaction.setAmount(BigDecimal.TEN);
        transaction.setIfscCode("IFSC123");
        transaction.setPurpose("Payment");
        transaction.setTimestamp(LocalDateTime.now());
        return transaction;
    }
}
