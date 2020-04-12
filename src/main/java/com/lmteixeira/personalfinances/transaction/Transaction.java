package com.lmteixeira.personalfinances.transaction;

import java.math.BigDecimal;

public class Transaction {

    private BigDecimal expenseValue;
    private String description;
    private Long timestamp;

    public Transaction(BigDecimal expenseValue, String description, Long timestamp) {
        this.expenseValue = expenseValue;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Boolean isLessThanZero() {
        return this.expenseValue.compareTo( BigDecimal.ZERO ) < 0;
    }

    public BigDecimal sum(BigDecimal valueToSum) {
        return expenseValue.add( valueToSum );
    }

    public BigDecimal sum(Transaction expenseToSum) {
        return expenseValue.add( expenseToSum.expenseValue );
    }

    public BigDecimal subtractFrom(BigDecimal valueToSubtractFrom) {
        return valueToSubtractFrom.subtract( this.expenseValue );
    }

    public Boolean isEqualTo(BigDecimal valueToCompare) {
        return expenseValue.compareTo( valueToCompare ) == 0;
    }

    public String getValueStringRepresentation() {
        return this.expenseValue.toPlainString();
    }

    public String getDescriptionStringRepresentation() {
        return description;
    }
}
