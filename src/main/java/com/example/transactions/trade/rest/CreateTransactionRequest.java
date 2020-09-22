package com.example.transactions.trade.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    private Long clientNumber;
    private BigDecimal amount;
    private String currency;
    private String type;
    private String description;
}
