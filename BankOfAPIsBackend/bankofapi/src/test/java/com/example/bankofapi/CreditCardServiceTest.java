package com.example.bankofapi;


import com.example.bankofapi.Model.CreditCard;
import com.example.bankofapi.Repository.CreditCardRepository;
import com.example.bankofapi.Service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardServiceTest {
    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHotlistCreditCard_NotFound() {
        // Mock the behavior of the creditCardRepository using when/thenReturn
        when(creditCardRepository.findById("1234567890123456")).thenReturn(java.util.Optional.ofNullable(null));

        // Call the service method
        boolean response = creditCardService.hotlistCreditCard("1234567890123456", "Stolen");

        // Verify the response
        verify(creditCardRepository, never()).save(any(CreditCard.class));
        assertFalse(response);
    }
}


