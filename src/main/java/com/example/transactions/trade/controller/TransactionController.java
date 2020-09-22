package com.example.transactions.trade.controller;

import com.example.transactions.trade.model.Trn;
import com.example.transactions.trade.rest.CreateTransactionRequest;
import com.example.transactions.trade.rest.EditTransactionRequest;
import com.example.transactions.trade.rest.SplitTransactionRequest;
import com.example.transactions.trade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping()
    ResponseEntity<Trn> createTransaction(@RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok(service.createTransaction(request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Integer> deleteTransaction(@PathVariable("id") Long id) {
        service.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Trn> editTransaction(@PathVariable("id") Long id, @RequestBody EditTransactionRequest request) {
        return ResponseEntity.ok(service.editTransaction(id, request));
    }

    @PostMapping("/split/{id}")
    ResponseEntity<List<Trn>> splitTransaction(@PathVariable("id") Long id, @RequestBody SplitTransactionRequest request) {
        return ResponseEntity.ok(service.splitTransaction(id, request));
    }

}
