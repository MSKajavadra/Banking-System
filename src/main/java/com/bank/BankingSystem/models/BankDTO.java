package com.bank.BankingSystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankDTO {
    private String acHolderName;
    private double acBalance;
    private long mobileNumber;
    private long aadharCardNumber;
}
