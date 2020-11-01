package com.lmteixeira.personalfinances.usecases.models;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;

public class IncomeModel {

    private BigDecimal value;
    private String description;
    private Long timestamp;

    private IncomeModel() {

    }

    public static IncomeModel fromTransaction(Transaction transaction) {
        IncomeModel incomeModel = new IncomeModel();
        incomeModel.value = transaction.getValue();
        incomeModel.description = transaction.getDescriptionStringRepresentation();
        incomeModel.timestamp = transaction.getTimestamp();
        return incomeModel;
    }

}
