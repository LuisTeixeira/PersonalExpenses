package com.lmteixeira.personalfinances.usecases.models;

import java.math.BigDecimal;

public class BudgetModel {

    BigDecimal balance;
    boolean isNegative;

    public BudgetModel(BigDecimal balance, boolean isNegative) {
        this.balance = balance;
        this.isNegative = isNegative;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isNegative() {
        return isNegative;
    }
}
