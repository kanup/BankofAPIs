package com.example.banktransaction.dao;

import com.example.banktransaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBeneficiaryBankAndBeneficiaryAccountNumberOrderByTimestampDesc(
            String beneficiaryBank, String beneficiaryAccountNumber);
    List<Transaction> findBySenderBankAndSenderAccountNumberOrderByTimestampDesc(
            String senderBank, String senderAccountNumber);
}
