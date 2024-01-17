package com.bank.BankingSystem.repository;

import com.bank.BankingSystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<Bank,Integer> {
    Bank findByAcNo(double acBalance);
}
