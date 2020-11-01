package com.lmteixeira.personalfinances.webadapter.model;

import com.lmteixeira.personalfinances.usecases.models.TransactionModel;

import java.math.BigDecimal;

public class TransactionWeb {

    private String description;
    private BigDecimal value;
    private Long timestamp;

    private TransactionWeb() {

    }

    public TransactionWeb(String description, Double value) {
        this.description = description;
        this.value = BigDecimal.valueOf(value);
    }

    public static TransactionWeb fromModel(TransactionModel expenseModel) {
        TransactionWeb transactionWeb = new TransactionWeb();
        transactionWeb.value = expenseModel.getValue();
        transactionWeb.description = expenseModel.getDescription();
        transactionWeb.timestamp = expenseModel.getTimestamp();
        return transactionWeb;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }
}
