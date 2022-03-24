package com.example.transfer_app.services;

import com.example.transfer_app.data.models.Wallet;
import com.example.transfer_app.web.exception.AccountNumberAlreadyExistException;
import com.example.transfer_app.web.exception.WalletException;

import java.util.List;

public interface WalletService {

    Wallet createOrUpdate(Wallet wallet) throws AccountNumberAlreadyExistException;

    List<Wallet> getAll();

    boolean delete(Long id) throws WalletException;

    Wallet getById(Long id) throws WalletException;
}
