package com.example.transfer_app.web.controller;

import com.example.transfer_app.data.models.Transaction;
import com.example.transfer_app.services.TransactionService;
import com.example.transfer_app.services.ValidationErrorService;
import com.example.transfer_app.web.exception.AccountNumberAlreadyExistException;
import com.example.transfer_app.web.exception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ValidationErrorService validationService;

    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result){
        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id, transaction);
        return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long wallet_id,@PathVariable Long id,@Valid @RequestBody Transaction transaction, BindingResult result){
        ResponseEntity<?> errors = validationService.validate(result);
        if(errors != null) return errors;
        transaction.setId(id);
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id,transaction);
        return new ResponseEntity<>(transactionSaved,HttpStatus.OK);
    }

    @DeleteMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long wallet_id,@PathVariable Long id) throws WalletException {
        transactionService.delete(wallet_id,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }


    @GetMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> getById(@PathVariable Long wallet_id,@PathVariable Long id) throws WalletException {
        return new ResponseEntity<>(transactionService.getById(wallet_id,id),HttpStatus.OK);
    }
}
