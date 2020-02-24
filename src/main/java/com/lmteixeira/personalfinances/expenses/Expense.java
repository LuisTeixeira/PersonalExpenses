package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;

public class Expense {

    protected BigDecimal expenseValue;
    private String description;
    private Long timestamp;

    public Expense(BigDecimal expenseValue, String description, Long timestamp) {
        this.expenseValue = expenseValue;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Boolean isLessThanZero() {
        return this.expenseValue.compareTo(BigDecimal.ZERO) < 0;
    }

    public BigDecimal sum(BigDecimal valueToSum){
        return expenseValue.add(valueToSum);
    }

    public BigDecimal subtractFrom(BigDecimal valueToSubtractFrom) {
        return valueToSubtractFrom.subtract(this.expenseValue);
    }
}
