package com.example.transfer_app.services;

import com.example.transfer_app.data.models.Wallet;
import com.example.transfer_app.data.repositories.WalletRepository;
import com.example.transfer_app.web.exception.AccountNumberAlreadyExistException;
import com.example.transfer_app.web.exception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createOrUpdate(Wallet wallet) throws AccountNumberAlreadyExistException {

        if (wallet == null){
            throw new IllegalArgumentException("Wallet cannot be null");
        }

        if (walletRepository.existsByAccountNumber(wallet.getAccountNumber())){
            throw new AccountNumberAlreadyExistException("Account already exist");
        }

        wallet.setAccountNumber(accountNumberGenerator());
        return walletRepository.save(wallet);
    }

    private String accountNumberGenerator() {
        SecureRandom mySecureRandom = new SecureRandom();
        String accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));
        while (walletRepository.findAll().contains(accountNumber)) {
            accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));
        }
        return accountNumber;
    }

    public boolean delete(Long id) throws WalletException {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            walletRepository.delete(wallet.get());
            return true;
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }

    public List<Wallet> getAll(){
        return walletRepository.findAllByOrderByPriority();
    }


    public Wallet getById(Long id) throws WalletException {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }
}
