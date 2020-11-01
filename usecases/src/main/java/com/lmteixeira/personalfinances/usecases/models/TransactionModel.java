package com.lmteixeira.personalfinances.usecases.models;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;

public class TransactionModel {
    private BigDecimal value;
    private String description;
    private Long timestamp;

    private TransactionModel() {

    }

    public static TransactionModel fromTransaction(Transaction transaction) {
        TransactionModel expenseModel = new TransactionModel();
        expenseModel.value = transaction.getValue();
        expenseModel.description = transaction.getDescriptionStringRepresentation();
        expenseModel.timestamp = transaction.getTimestamp();
        return expenseModel;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
