package com.example.transfer_app.data.repositories;

import com.example.transfer_app.data.models.Transaction;
import com.example.transfer_app.data.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByWallet(Wallet wallet);
}
