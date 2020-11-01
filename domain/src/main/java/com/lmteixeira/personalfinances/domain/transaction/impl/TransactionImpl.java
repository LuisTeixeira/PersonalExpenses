package com.lmteixeira.personalfinances.domain.transaction.impl;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;

public class TransactionImpl implements Transaction {

    private BigDecimal expenseValue;
    private String description;
    private Long timestamp;

    public TransactionImpl(BigDecimal expenseValue, String description, Long timestamp) {
        this.expenseValue = expenseValue;
        this.description = description;
        this.timestamp = timestamp;
    }

    @Override
    public BigDecimal getValue() {
        return expenseValue;
    }

    @Override
    public Boolean isLessThanZero() {
        return this.expenseValue.compareTo( BigDecimal.ZERO ) < 0;
    }

    @Override
    public BigDecimal sum(BigDecimal valueToSum) {
        return expenseValue.add( valueToSum );
    }

    @Override
    public BigDecimal sum(Transaction expenseToSum) {
        return expenseValue.add( expenseToSum.getValue() );
    }

    @Override
    public BigDecimal subtractFrom(BigDecimal valueToSubtractFrom) {
        return valueToSubtractFrom.subtract( this.expenseValue );
    }

    @Override
    public Boolean isEqualTo(BigDecimal valueToCompare) {
        return expenseValue.compareTo( valueToCompare ) == 0;
    }

    @Override
    public String getValueStringRepresentation() {
        return this.expenseValue.toPlainString();
    }

    @Override
    public String getDescriptionStringRepresentation() {
        return description;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}
