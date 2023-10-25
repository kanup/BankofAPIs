package com.example.banktransaction.controller;

import com.example.banktransaction.response.BasicResponseMsg;
import com.example.banktransaction.response.TransactionRequest;
import com.example.banktransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RequestMapping("/transactions")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/saveTransaction")
    public ResponseEntity<BasicResponseMsg> saveTransaction(@RequestBody TransactionRequest transactionRequest){
        BasicResponseMsg response = new BasicResponseMsg();
        try{
            response = transactionService.saveTransaction(transactionRequest);

        }catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("getTransaction")
//    public ResponseEntity<BasicResponseMsg> getTransaction(@RequestBody TransactionRequest transactionRequest){
//        BasicResponseMsg response = new BasicResponseMsg();
//        try{
//            response = transactionService.getTransaction(transactionRequest);
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
@GetMapping("/getTransaction")
public ResponseEntity<BasicResponseMsg> getTransaction(@RequestParam(name = "senderBank") String senderBank,
                                                       @RequestParam(name = "senderAccountNumber") String senderAccountNumber){

    BasicResponseMsg response = new BasicResponseMsg();
    try{
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setSenderBank(senderBank);
        transactionRequest.setSenderAccountNumber(senderAccountNumber);

        response = transactionService.getTransaction(transactionRequest);


    }catch (Exception e){
        e.printStackTrace();

    }
    return new ResponseEntity<>(response, HttpStatus.OK);
}


}
