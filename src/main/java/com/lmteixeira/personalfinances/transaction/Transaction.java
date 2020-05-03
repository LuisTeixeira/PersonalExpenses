package com.lmteixeira.personalfinances.transaction;

import com.lmteixeira.personalfinances.transaction.impl.TransactionImpl;

import java.math.BigDecimal;

public interface Transaction {
    Boolean isLessThanZero();

    BigDecimal getExpenseValue();

    BigDecimal sum(BigDecimal valueToSum);

    BigDecimal sum(Transaction expenseToSum);

    BigDecimal subtractFrom(BigDecimal valueToSubtractFrom);

    Boolean isEqualTo(BigDecimal valueToCompare);

    String getValueStringRepresentation();

    String getDescriptionStringRepresentation();
}
