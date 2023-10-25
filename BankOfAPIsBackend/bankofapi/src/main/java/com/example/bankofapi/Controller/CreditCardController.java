package com.example.bankofapi.Controller;

import com.example.bankofapi.Model.CreditCard;
import com.example.bankofapi.Service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/creditCard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/getCreditCards/{phoneNumber}")
    public List<CreditCard> getCreditCardsByPhoneNumber(@PathVariable String phoneNumber) {
        return creditCardService.getCreditCardNumbersByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getCreditCard/{creditCardNumber}")
    public CreditCard getCreditCardDetails(@PathVariable String creditCardNumber) {
        CreditCard creditCard = creditCardService.getCreditCardByNumber(creditCardNumber);
        return creditCard;
    }

    @PostMapping("/hotlist/{creditCardNumber}/{reason}")
    public ResponseEntity<String> hotlistCreditCard(@PathVariable String creditCardNumber, @PathVariable String reason) {
        boolean hotlistingResult = creditCardService.hotlistCreditCard(creditCardNumber, reason);

        if (hotlistingResult) {
            return ResponseEntity.ok("Credit card hotlisted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
