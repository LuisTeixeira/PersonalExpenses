package com.lmteixeira.personalfinances.hazelcastrepo.model;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;

import java.io.Serializable;
import java.math.BigDecimal;

public class HazelcastTransaction implements Serializable {

    private static final long serialVerisionUID = 1L;

    private BigDecimal expenseValue;
    private String description;
    private Long timestamp;

    public static HazelcastTransaction fromTransaction(Transaction transaction) {
        HazelcastTransaction hazelcastTransaction = new HazelcastTransaction();
        hazelcastTransaction.expenseValue = transaction.getValue();
        hazelcastTransaction.description = transaction.getDescriptionStringRepresentation();
        hazelcastTransaction.timestamp = transaction.getTimestamp();
        return hazelcastTransaction;
    }

    public Transaction toTransaction() {
        return new TransactionFactory().createTransaction(expenseValue, description, timestamp);
    }
}
