package com.example.transactions.trade.service;

import com.example.transactions.trade.model.Trn;
import com.example.transactions.trade.repository.TrnRepository;
import com.example.transactions.trade.rest.CreateTransactionRequest;
import com.example.transactions.trade.rest.EditTransactionRequest;
import com.example.transactions.trade.rest.SplitTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TransactionService {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TrnRepository trnRepository;

    public Trn createTransaction(CreateTransactionRequest request) {
        Trn trn = Trn.builder()
                .clientNumber(request.getClientNumber())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .type(request.getType())
                .description(request.getDescription())
                .build();
        return trnRepository.save(trn);
    }

    public void deleteTransaction(Long id) {
        trnRepository.deleteById(id);
    }

    public Trn editTransaction(Long id, EditTransactionRequest request) {
        Optional<Trn> trn = trnRepository.findById(id);
        if (trn.isEmpty()) {
            throw new IllegalArgumentException("Id not present");
        }
        Trn editTransaction = trn.get();
        if (request.getAmount() != null) {
            editTransaction.setAmount(request.getAmount());
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            editTransaction.setType(request.getType());
        }
        if (request.getDescription() != null) {
            editTransaction.setDescription(request.getDescription());
        }
        return trnRepository.save(editTransaction);
    }

    @Transactional
    public List<Trn> splitTransaction(Long id, SplitTransactionRequest request) {
        Optional<Trn> optionalTrn = trnRepository.findById(id);
        if (optionalTrn.isEmpty()) {
            throw new IllegalArgumentException("Id not present");
        }
        Trn firstTransaction = optionalTrn.get();
        BigDecimal newAmount = firstTransaction.getAmount().divide(new BigDecimal("2"));
        firstTransaction.setAmount(newAmount);
        Trn newTransaction = Trn.builder()
                .clientNumber(firstTransaction.getClientNumber())
                .amount(newAmount)
                .currency(firstTransaction.getCurrency())
                .build();
        newTransaction.setType(request.getType() != null && !request.getType().isEmpty() ?
                request.getType() : firstTransaction.getType());

        newTransaction.setDescription(request.getDescription() != null ?
                request.getDescription() : firstTransaction.getDescription());

        List<Trn> result = new ArrayList<>();
        trnRepository.saveAll(Arrays.asList(firstTransaction, newTransaction)).forEach(result::add);
        return result;
    }

    public List<Trn> findByClientNumberAndOrType(Long clientNumber, String type) {
        if (clientNumber == null && type == null) {
            throw new IllegalArgumentException("clientNumber or type are required");
        }
        List<Trn> response;
        if (clientNumber != null && type != null && !type.isEmpty()) {
            return trnRepository.findAllByClientNumberAndType(clientNumber, type);
        }
        if (type != null && !type.isEmpty()) {
            return trnRepository.findAllByType(type);
        } else {
            return trnRepository.findAllByClientNumber(clientNumber);
        }
    }

    public Map<String, BigDecimal> amountSumByClientNumberAndOrType(Long clientNumber, String type) {
        List<Trn> trnList = findByClientNumberAndOrType(clientNumber, type);
        return amountSum(trnList);
    }

    public List<Trn> findByClientNumberAndDateBetween(Long clientNumber, String fromDate, String toDate) throws ParseException {
        if (clientNumber == null || fromDate == null || toDate == null) {
            throw new IllegalArgumentException("clientNumber, fromDate, toDate are required");
        }
        return trnRepository.findAllByClientNumberAndCreationDateBetween(
                clientNumber, FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
    }

    public Long countByClientNumberAndCreationDateBetween(Long clientNumber, String fromDate, String toDate) throws ParseException {
        if (clientNumber == null || fromDate == null || toDate == null) {
            throw new IllegalArgumentException("clientNumber, fromDate, toDate are required");
        }
        return trnRepository.countByClientNumberAndCreationDateBetween(
                clientNumber, FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
    }

    public Map<String, BigDecimal> amountSumByClientNumberAndCreationDateBetween(
            Long clientNumber, String fromDate, String toDate) throws ParseException {
        List<Trn> trnList = findByClientNumberAndDateBetween(clientNumber, fromDate, toDate);
        return amountSum(trnList);
    }

    public List<Trn> findByClientNumbers(List<Long> clientNumbers) {
        if (clientNumbers == null) {
            throw new IllegalArgumentException("clientNumbers are required");
        }
        return trnRepository.findAllByClientNumberIn(clientNumbers);
    }

    public Map<String, BigDecimal> amountSumByClientNumbersAndTypesIn(List<Long> clientNumbers, List<String> types) {
        if (clientNumbers == null || types == null) {
            throw new IllegalArgumentException("clientNumbers and types are required");
        }
        List<Trn> trnList = trnRepository.findAllByClientNumberInAndTypeIn(clientNumbers, types);
        return amountSum(trnList);
    }

    public Map<String, BigDecimal> amountSumByClientNumbersAndTypesNotIn(List<Long> clientNumbers, List<String> types) {
        if (clientNumbers == null || types == null) {
            throw new IllegalArgumentException("clientNumbers and types are required");
        }
        List<Trn> trnList = trnRepository.findAllByClientNumberInAndTypeNotIn(clientNumbers, types);
        return amountSum(trnList);
    }

    public List<Trn> findByTypeAndOrAndCreationDateBetween(String type, String fromDate, String toDate) throws ParseException {
        if (type != null && !type.isEmpty() && fromDate != null && toDate != null) {
            return trnRepository.findAllByTypeAndCreationDateBetween(
                    type, FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
        }
        if (type != null && !type.isEmpty()) {
            return trnRepository.findAllByType(type);
        }
        if (fromDate != null && toDate != null) {
            return trnRepository.findAllByCreationDateBetween(FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
        }
        throw new IllegalArgumentException("type or fromDate and toDate are required");
    }

    public Long countByClientNumberAndOrCreationDateBetween(String type, String fromDate, String toDate) throws ParseException {
        if (type != null && !type.isEmpty() && fromDate != null && toDate != null) {
            return trnRepository.countByTypeAndCreationDateBetween(
                    type, FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
        }
        if (type != null && !type.isEmpty()) {
            return trnRepository.countByType(type);
        }
        if (fromDate != null && toDate != null) {
            return trnRepository.countByCreationDateBetween(FORMATTER.parse(fromDate), FORMATTER.parse(toDate));
        }
        throw new IllegalArgumentException("type or fromDate and toDate are required");
    }

    private Map<String, BigDecimal> amountSum(List<Trn> trnList) {
        if (trnList == null) {
            throw new IllegalArgumentException("List is null");
        }
        Map<String, BigDecimal> result = new HashMap<>();
        trnList.forEach(l -> result.put(l.getCurrency(),
                result.get(l.getCurrency()) != null ? result.get(l.getCurrency()).add(l.getAmount()) : l.getAmount()));
        return result;
    }
}
