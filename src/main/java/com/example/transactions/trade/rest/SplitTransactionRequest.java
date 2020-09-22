package com.example.transactions.trade.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitTransactionRequest {
    private String type;
    private String description;
}
