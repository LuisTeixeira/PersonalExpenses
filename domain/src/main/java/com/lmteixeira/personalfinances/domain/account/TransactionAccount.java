package com.lmteixeira.personalfinances.domain.account;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionAccount {
    BigDecimal getTotal();

    void addTransaction(Transaction expense);

    Long getTransactionCount();

    void remove(Transaction expense);

    BigDecimal getAverage();

    List<String> getTransactionDescriptions();

    boolean isEmpty();

    List<Transaction> getTransactions();
}
