package com.example.transfer_app.web.controller;

import com.example.transfer_app.data.models.Wallet;
import com.example.transfer_app.services.ValidationErrorService;
import com.example.transfer_app.services.WalletService;
import com.example.transfer_app.web.exception.AccountNumberAlreadyExistException;
import com.example.transfer_app.web.exception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationService;

    @PostMapping()
    public ResponseEntity<?>create(@Valid @RequestBody Wallet wallet, BindingResult result) throws AccountNumberAlreadyExistException {

        try {
            ResponseEntity<?> errors = validationService.validate(result);
            if (errors != null) return errors;
            Wallet walletSaved = walletService.createOrUpdate(wallet);
            return new ResponseEntity<>(walletSaved, HttpStatus.CREATED);
        }catch (AccountNumberAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result) throws AccountNumberAlreadyExistException {
        try {
            ResponseEntity<?> errors = validationService.validate(result);
            if (errors != null) return errors;
            wallet.setId(id);
            Wallet walletSaved = walletService.createOrUpdate(wallet);
            return new ResponseEntity<>(walletSaved, HttpStatus.OK);
        }catch (AccountNumberAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws WalletException {

        Wallet getWallet = walletService.getById(id);
        return new ResponseEntity<>(getWallet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws WalletException {
        boolean deletedWallet = walletService.delete(id);
        return new ResponseEntity<>(deletedWallet, HttpStatus.OK);
    }
}
