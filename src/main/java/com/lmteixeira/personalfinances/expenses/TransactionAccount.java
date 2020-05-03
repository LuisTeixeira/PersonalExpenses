package com.lmteixeira.personalfinances.expenses;

import com.lmteixeira.personalfinances.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionAccount {
    BigDecimal getTotal();

    void addTransaction(Transaction expense);

    Long getTransactionCount();

    void remove(Transaction expense);

    BigDecimal getAverage();

    List<String> getTransactionDescriptions();
}
