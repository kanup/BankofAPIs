package com.example.banktransaction.serviceImpl;

import com.example.banktransaction.dao.TransactionDao;
import com.example.banktransaction.model.BankStatement;
import com.example.banktransaction.model.Transaction;
import com.example.banktransaction.response.BasicResponseMsg;
import com.example.banktransaction.response.TransactionRequest;
import com.example.banktransaction.service.TransactionService;
import  com.example.banktransaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl  implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;
    @Override
    public BasicResponseMsg saveTransaction(TransactionRequest transactionRequest) {
        BasicResponseMsg response = new BasicResponseMsg();
        try {
            Transaction transaction = new Transaction();
            transaction.setSenderAccountNumber(transactionRequest.getSenderAccountNumber());
            transaction.setBeneficiaryBank(transactionRequest.getBeneficiaryBank());
            transaction.setBeneficiaryAccountNumber(transactionRequest.getBeneficiaryAccountNumber());
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setIfscCode(transactionRequest.getIfscCode());
            transaction.setSenderBank(transactionRequest.getSenderBank());
            transaction.setPurpose(transactionRequest.getPurpose());
            Transaction save = transactionDao.save(transaction);
            response.setData(save.getId());
            response.setMessage("Transaction saved");
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public BasicResponseMsg getTransaction(TransactionRequest transactionRequest) {
        BasicResponseMsg response = new BasicResponseMsg();
        try {


            List<Transaction> bankStatements_deposits = transactionDao
                    .findByBeneficiaryBankAndBeneficiaryAccountNumberOrderByTimestampDesc(
                            transactionRequest.getSenderBank(),
                            transactionRequest.getSenderAccountNumber());
            List<Transaction> bankStatements_withdrawal = transactionDao
                    .findBySenderBankAndSenderAccountNumberOrderByTimestampDesc(
                            transactionRequest.getSenderBank(),
                            transactionRequest.getSenderAccountNumber());


            if (bankStatements_deposits.isEmpty() && bankStatements_withdrawal.isEmpty()) {
                response.setStatus(400);
                response.setMessage("No bank statements found");

            } else {
                response.setStatus(200);
                response.setMessage("Bank statements found");
                List<BankStatement> combinedStatements = new ArrayList<>();
                combinedStatements.addAll(convertToBankStatementTableFormat(bankStatements_deposits, "beneficiary"));
                combinedStatements.addAll(convertToBankStatementTableFormat(bankStatements_withdrawal, "sender"));

                response.setData(combinedStatements);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<BankStatement> convertToBankStatementTableFormat(List<Transaction> transactions, String type) {
        List<BankStatement> bankStatements = new ArrayList<>();
        for (Transaction transaction : transactions) {
            BankStatement bankStatement = new BankStatement();

            LocalDateTime localDateTime = transaction.getTimestamp();
            Date date = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            bankStatement.setDate(date);
            bankStatement.setPurpose(transaction.getPurpose());

            BigDecimal amount = transaction.getAmount();
            if( type.equals("beneficiary")){
                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    bankStatement.setWithdrawal(amount.abs());
                    bankStatement.setDeposit(null);
                } else {
                    bankStatement.setWithdrawal(null);
                    bankStatement.setDeposit(amount);
                }

            }else{
                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    bankStatement.setWithdrawal(null);
                    bankStatement.setDeposit(amount.abs());
                } else {
                    bankStatement.setWithdrawal(amount);
                    bankStatement.setDeposit(null);
                }

            }
            bankStatements.add(bankStatement);

        }
        return bankStatements;
    }
//    public BasicResponseMsg getTransaction(TransactionRequest transactionRequest) {
//        BasicResponseMsg response = new BasicResponseMsg();
//        try {
//            Optional<Transaction> findByID = transactionDao.findById(Long.valueOf(transactionRequest.getId()));
//            if(findByID.isPresent()){
//                Transaction transaction = findByID.get();
//                response.setStatus(200);
//                response.setMessage("data found");
//                response.setData((transaction));
//            }else {
//                response.setStatus(400);
//                response.setMessage("data not found");
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return response;
//    }
}
