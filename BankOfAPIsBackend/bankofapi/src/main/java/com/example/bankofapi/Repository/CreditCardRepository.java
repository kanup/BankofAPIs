package com.example.bankofapi.Repository;

import com.example.bankofapi.Model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    CreditCard findByCreditCardNumber(String creditCardNumber);
}
