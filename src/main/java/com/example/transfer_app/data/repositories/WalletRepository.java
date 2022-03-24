package com.example.transfer_app.data.repositories;

import com.example.transfer_app.data.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByOrderByPriority();

    boolean existsByAccountNumber(String accountNumber);

}
