package com.example.bankofapi.Service;

import com.example.bankofapi.Model.CreditCard;
import com.example.bankofapi.Repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCard> getCreditCardNumbersByPhoneNumber(String phoneNumber) {

        List<CreditCard> creditCards = creditCardRepository.findAll();
        List<CreditCard> filteredCards= creditCards.stream()
                .filter(u -> u.getPhoneNumber().trim().equalsIgnoreCase(phoneNumber.trim()) && "Active".equals(u.getStatus()))
                .collect(Collectors.toList());

        return filteredCards;
    }

    public CreditCard getCreditCardByNumber(String creditCardNumber) {
        List<CreditCard> creditCards = creditCardRepository.findAll();

        CreditCard filteredCard = creditCards.stream()
                .filter(u -> u.getCreditCardNumber().trim().equals(creditCardNumber.trim()))
                .findFirst() // Find the first matching CreditCard or return null if none is found
                .orElse(null);

        return filteredCard;
    }

    public boolean hotlistCreditCard(String creditCardNumber, String reason) {
        CreditCard creditCard = creditCardRepository.findById(creditCardNumber).orElse(null);

        if (creditCard != null) {
            // Update the status to "Inactive" and the reason for hotlisting
            creditCard.setStatus("Inactive");
            creditCard.setReasonForHotlisting(reason);
            creditCardRepository.save(creditCard);
            return true; // Hotlisting was successful
        } else {
            return false; // Credit card not found
        }
    }
}
