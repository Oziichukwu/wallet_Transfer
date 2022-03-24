package com.example.transfer_app.services;

import com.example.transfer_app.data.models.Transaction;
import com.example.transfer_app.web.exception.WalletException;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll(Long walletId);

    Transaction createOrUpdate(Long walletId, Transaction transaction);

    boolean delete(Long wallet_id,Long id) throws WalletException;

    Transaction getById(Long wallet_id,Long id) throws WalletException;
}
