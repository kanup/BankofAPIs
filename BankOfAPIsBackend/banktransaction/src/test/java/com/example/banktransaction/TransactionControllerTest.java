package com.example.banktransaction;

import com.example.banktransaction.controller.TransactionController;
import com.example.banktransaction.response.BasicResponseMsg;
import com.example.banktransaction.response.TransactionRequest;
import com.example.banktransaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

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

        // Mock the behavior of the transactionService using doReturn
        BasicResponseMsg response = new BasicResponseMsg();
        doReturn(response).when(transactionService).saveTransaction(request);

        // Call the controller method
        ResponseEntity<BasicResponseMsg> controllerResponse = transactionController.saveTransaction(request);

        // Verify the response and service interaction
        verify(transactionService).saveTransaction(request);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
    }

    @Test
    public void testGetTransaction() {
        // Mock the behavior of the transactionService using doReturn
        BasicResponseMsg response = new BasicResponseMsg();
        doReturn(response).when(transactionService).getTransaction(any());

        // Call the controller method
        ResponseEntity<BasicResponseMsg> controllerResponse = transactionController.getTransaction("Sample Bank", "123456789");

        // Verify the response and service interaction
        verify(transactionService).getTransaction(any());
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
    }
}

