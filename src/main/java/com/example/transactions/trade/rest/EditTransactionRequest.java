package com.example.transactions.trade.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTransactionRequest {
    private BigDecimal amount;
    private String type;
    private String description;
}
