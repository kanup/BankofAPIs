package com.example.bankofapi.Controller;


import com.example.bankofapi.Dto.PaymentRequest;
import com.example.bankofapi.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// PaymentController.java
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment/{phoneNumber}/{creditCardNumber}/{accountNumber}/{amount}")
    public ResponseEntity<String> makePayment(@PathVariable String phoneNumber, @PathVariable String creditCardNumber,
                                              @PathVariable String accountNumber, @PathVariable double amount) {
        boolean paymentResult = paymentService.makePayment(phoneNumber,creditCardNumber,accountNumber,amount);

        if (paymentResult) {
            return ResponseEntity.ok("Payment successful.");
        } else {
            return ResponseEntity.badRequest().body("Payment failed.");
        }
    }
}
