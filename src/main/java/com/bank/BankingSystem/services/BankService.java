package com.bank.BankingSystem.services;


import com.bank.BankingSystem.models.Bank;
import com.bank.BankingSystem.models.BankDTO;
import com.bank.BankingSystem.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankService {
    @Autowired
    BankRepo bankRepo;
    public Bank openAccount(BankDTO bankDTO) {
        long acNo=acNoGenerator();

        String acHolderName=bankDTO.getAcHolderName();
        double acBalance=bankDTO.getAcBalance();
        long mobileNumber=bankDTO.getMobileNumber();
        long aadharCardNumber=bankDTO.getAadharCardNumber();

        Bank bank=new Bank(acNo,acHolderName,acBalance,mobileNumber,aadharCardNumber);
        return bankRepo.save(bank);
    }
    private long acNoGenerator(){
        Random random=new Random();
        long no=random.nextLong(9999L);
        return (14490100250000L+no);
    }

    public ResponseEntity<?> withdrawMoney(long acNo,double amount) {
        Bank bank=bankRepo.findByAcNo(acNo);
        if(bank==null){
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }else if(bank.getAcBalance()-amount<0){
            return new ResponseEntity<>("Insufficient Balance",HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            bank.setAcBalance(bank.getAcBalance()-amount);
            return new ResponseEntity<>(bankRepo.save(bank),HttpStatus.OK);
        }
    }

    public ResponseEntity<?> depositMoney(long acNo,double amount) {
        Bank bank=bankRepo.findByAcNo(acNo);
        if(bank==null){
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }else {
            bank.setAcBalance(bank.getAcBalance()+amount);
            return new ResponseEntity<>(bankRepo.save(bank),HttpStatus.OK);
        }
    }

    public ResponseEntity<?> balanceInquiry(long acNo) {
        Bank bank=bankRepo.findByAcNo(acNo);
        if(bank==null){
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(bank.getAcBalance(),HttpStatus.OK);
        }
    }

    public ResponseEntity<?> transferMoney(long senderAcNo, long receiverAcNo, double amount) {
        Bank sender=bankRepo.findByAcNo(senderAcNo);
        Bank receiver=bankRepo.findByAcNo(receiverAcNo);
        if(sender==null){
            return new ResponseEntity<>("Sender Not Found",HttpStatus.NOT_FOUND);
        } else if(receiver==null){
            return new ResponseEntity<>("Receiver Not Found",HttpStatus.NOT_FOUND);
        } else if(sender.getAcBalance()-amount<0){
            return new ResponseEntity<>("Insufficient Balance",HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            receiver.setAcBalance(receiver.getAcBalance()+amount);
            sender.setAcBalance(sender.getAcBalance()- amount);

            Map<String,Bank> map=new HashMap<>();
            map.put("Sender",bankRepo.save(sender));
            map.put("Receiver",bankRepo.save(receiver));
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    }
}
