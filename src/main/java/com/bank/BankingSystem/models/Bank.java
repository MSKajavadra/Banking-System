package com.bank.BankingSystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private long acNo;
    private String acHolderName;
    private double acBalance;
    private long mobileNumber;
    private long aadharCardNumber;

    public Bank(long acNo, String acHolderName, double acBalance, long mobileNumber, long aadharCardNumber) {
        this.acNo = acNo;
        this.acHolderName = acHolderName;
        this.acBalance = acBalance;
        this.mobileNumber = mobileNumber;
        this.aadharCardNumber = aadharCardNumber;
    }
}
