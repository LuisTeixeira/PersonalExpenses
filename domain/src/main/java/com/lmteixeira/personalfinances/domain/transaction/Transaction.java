package com.lmteixeira.personalfinances.domain.transaction;

import java.math.BigDecimal;

public interface Transaction {
    Boolean isLessThanZero();

    BigDecimal getValue();

    BigDecimal sum(BigDecimal valueToSum);

    BigDecimal sum(Transaction expenseToSum);

    BigDecimal subtractFrom(BigDecimal valueToSubtractFrom);

    Boolean isEqualTo(BigDecimal valueToCompare);

    String getValueStringRepresentation();

    String getDescriptionStringRepresentation();

    Long getTimestamp();
}
