package com.example.transactions.trade.controller;

import com.example.transactions.trade.model.Trn;
import com.example.transactions.trade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionSearchController {

    @Autowired
    private TransactionService service;

    @GetMapping("/findByClientNumberAndOrType")
    ResponseEntity<List<Trn>> findByClientNumberAndOrType(@RequestParam Long clientNumber,
                                                          @RequestParam String type){
        List<Trn> response;
        try {
            response = service.findByClientNumberAndOrType(clientNumber, type);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amountSumByClientNumberAndOrType")
    ResponseEntity<Map<String, BigDecimal>> amountSumByClientNumberAndOrType(@RequestParam Long clientNumber,
                                                    @RequestParam String type){
        Map<String, BigDecimal> response;
        try {
            response = service.amountSumByClientNumberAndOrType(clientNumber, type);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByClientNumberAndDateBetween")
    ResponseEntity<List<Trn>> findByClientNumberAndDateBetween(@RequestParam Long clientNumber,
                                                               @RequestParam String fromDate,
                                                               @RequestParam String toDate){
        List<Trn> response;
        try {
            response = service.findByClientNumberAndDateBetween(clientNumber,
                    fromDate, toDate);
        } catch (IllegalArgumentException | ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/countByClientNumberAndCreationDateBetween")
    ResponseEntity<Long> countByClientNumberAndCreationDateBetween(@RequestParam Long clientNumber,
                                                               @RequestParam String fromDate,
                                                               @RequestParam String toDate){
        Long response;
        try {
            response = service.countByClientNumberAndCreationDateBetween(clientNumber,
                    fromDate, toDate);
        } catch (IllegalArgumentException | ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amountSumByClientNumberAndCreationDateBetween")
    ResponseEntity<Map<String, BigDecimal>> amountSumByClientNumberAndCreationDateBetween(@RequestParam Long clientNumber,
                                                                                          @RequestParam String fromDate,
                                                                                          @RequestParam String toDate){
        Map<String, BigDecimal> response;
        try {
            response = service.amountSumByClientNumberAndCreationDateBetween(clientNumber,
                    fromDate, toDate);
        } catch (IllegalArgumentException | ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByClientNumbers")
    ResponseEntity<List<Trn>> findByClientNumbers(@RequestParam List<Long> clientNumbers){
        List<Trn> response;
        try {
            response = service.findByClientNumbers(clientNumbers);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amountSumByClientNumbersAndTypesIn")
    ResponseEntity<Map<String, BigDecimal>> amountSumByClientNumbersAndTypesIn(@RequestParam List<Long> clientNumbers,
                                                                 @RequestParam List<String> types){
        Map<String, BigDecimal> response;
        try {
            response = service.amountSumByClientNumbersAndTypesIn(clientNumbers, types);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amountSumByClientNumbersAndTypesNotIn")
    ResponseEntity<Map<String, BigDecimal>> amountSumByClientNumbersAndTypesNotIn(@RequestParam List<Long> clientNumbers,
                                                                               @RequestParam List<String> types){
        Map<String, BigDecimal> response;
        try {
            response = service.amountSumByClientNumbersAndTypesNotIn(clientNumbers, types);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByTypeAndOrAndCreationDateBetween")
    ResponseEntity<List<Trn>> findByTypeAndOrAndCreationDateBetween(@RequestParam String type,
                                                  @RequestParam String fromDate,
                                                  @RequestParam String toDate){
        List<Trn> response;
        try {
            response = service.findByTypeAndOrAndCreationDateBetween(type, fromDate, toDate);
        } catch (IllegalArgumentException | ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/countByClientNumberAndOrCreationDateBetween")
    ResponseEntity<Long> countByClientNumberAndOrCreationDateBetween(@RequestParam String type,
                                                                   @RequestParam String fromDate,
                                                                   @RequestParam String toDate){
        Long response;
        try {
            response = service.countByClientNumberAndOrCreationDateBetween(type,
                    fromDate, toDate);
        } catch (IllegalArgumentException | ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
}
