package com.bank.BankingSystem.controllers;

import com.bank.BankingSystem.models.Bank;
import com.bank.BankingSystem.models.BankDTO;
import com.bank.BankingSystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    BankService bankService;

    @PostMapping("open-account")
    public ResponseEntity<Bank> openAccount(@RequestBody BankDTO bankDTO){
        try {
            return new ResponseEntity<>(bankService.openAccount(bankDTO), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("withdraw-money")
    public ResponseEntity<?> withdrawMoney(@RequestParam long acNo,double amount){
        return bankService.withdrawMoney(acNo,amount);
    }
    @PutMapping("deposit-money")
    public ResponseEntity<?> depositMoney(@RequestParam long acNo,double amount){
        return bankService.depositMoney(acNo,amount);
    }
    @PutMapping("transfer-money")
    public ResponseEntity<?> transferMoney(@RequestParam long senderAcNo,long receiverAcNo,double amount){
        return bankService.transferMoney(senderAcNo,receiverAcNo,amount);
    }
    @GetMapping("balance-inquiry/{acNo}")
    public ResponseEntity<?> balanceInquiry(@PathVariable long acNo){
        return bankService.balanceInquiry(acNo);
    }
}
