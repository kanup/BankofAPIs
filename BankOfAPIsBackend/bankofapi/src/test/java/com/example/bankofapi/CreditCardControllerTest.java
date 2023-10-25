package com.example.bankofapi;

import com.example.bankofapi.Controller.CreditCardController;
import com.example.bankofapi.Model.CreditCard;
import com.example.bankofapi.Service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardControllerTest {
    @InjectMocks
    private CreditCardController creditCardController;

    @Mock
    private CreditCardService creditCardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHotlistCreditCard_Success() {
        // Mock the behavior of the creditCardService using when/thenReturn
        when(creditCardService.hotlistCreditCard("1234567890123456", "Stolen")).thenReturn(true);

        // Call the controller method
        ResponseEntity<String> response = creditCardController.hotlistCreditCard("1234567890123456", "Stolen");

        // Verify the response
        verify(creditCardService, times(1)).hotlistCreditCard("1234567890123456", "Stolen");
        assertEquals("Credit card hotlisted successfully.", response.getBody());
    }

    @Test
    public void testHotlistCreditCard_NotFound() {
        // Mock the behavior of the creditCardService using when/thenReturn
        when(creditCardService.hotlistCreditCard("1234567890123456", "Stolen")).thenReturn(false);

        // Call the controller method
        ResponseEntity<String> response = creditCardController.hotlistCreditCard("1234567890123456", "Stolen");

        // Verify the response
        verify(creditCardService, times(1)).hotlistCreditCard("1234567890123456", "Stolen");
        assertEquals(404, response.getStatusCodeValue());
    }
}

