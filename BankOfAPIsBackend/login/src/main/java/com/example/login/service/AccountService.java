package com.example.login.service;

import com.example.login.dao.AccountDao;
import com.example.login.model.Account;
import com.example.login.request.AccountRequest;
import com.example.login.request.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<Account> getAccounts(){
        return (List<Account>) accountDao.findAll();
    }

    public List<Account> getAccountByPhoneNumberAndBank( String phoneNumber, String bankName){
        List<Account> accounts = (List<Account>) accountDao.findAll();
        accounts = accounts.stream().filter(account -> account.getPhoneNumber().equals(phoneNumber) && account.getBankName().equals(bankName)).collect(Collectors.toList());
        return accounts;
    }

    public List<Account> getAccountByPhoneNumber(String phoneNumber) {
       return  ((List<Account>) accountDao.findAll()).stream().filter(a -> a.getPhoneNumber().equals(phoneNumber)).collect(Collectors.toList());
    }

    public BasicResponse updateAccount(AccountRequest accountrequest) {
        String bankName = accountrequest.getBankName();
        Long accountNo = accountrequest.getAccountNo();
        double amount = accountrequest.getAmount();
        Optional<Account> existingAccount = accountDao.findByBankNameAndAccountNo(bankName, accountNo);
        BasicResponse msg = new BasicResponse();
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            double newBalance = account.getBalance();
            if( accountrequest.getType().equals("w")){
                newBalance  -= amount;
            }else{
                newBalance  += amount;
            }

            if (newBalance >= 0) {
                account.setBalance(newBalance);
                Account save = accountDao.save(account);
                msg.setMessage("success");
                msg.setData(save);
                return msg;
            } else {
                // Handle insufficient balance error
                msg.setMessage("insufficient");
                return msg;
            }

        } else {
            msg.setMessage("not found");
            return msg;
        }
//        try {
//            Optional<Account> optionalAccount = accountDao.findById(Integer.valueOf(accountrequest.getId()));
//            if (optionalAccount.isPresent()) {
//                Account account = optionalAccount.get();
//                double newBalance = accountrequest.getAmount();
//                account.setBalance(newBalance);
//                accountDao.save(account);
//                return String.valueOf(HttpStatus.OK);
//            } else {
//                return String.valueOf(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return String.valueOf(HttpStatus.BAD_REQUEST); // Return a different status code for errors
//        }
    }
}
