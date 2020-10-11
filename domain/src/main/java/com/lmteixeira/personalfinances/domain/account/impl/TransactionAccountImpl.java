package com.lmteixeira.personalfinances.domain.account.impl;

import com.lmteixeira.personalfinances.domain.account.TransactionAccount;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAccountImpl implements TransactionAccount {

    private BigDecimal total = BigDecimal.valueOf( 0 );
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void addTransaction(Transaction expense) {
        if ( expense.isLessThanZero() ) {
            throw new InvalidTransactionValueException( "Value " + expense.getValueStringRepresentation() + " is less than zero" );
        }
        total = expense.sum( total );
        transactions.add( expense );
    }

    @Override
    public Long getTransactionCount() {
        return Long.valueOf( transactions.size() );
    }

    @Override
    public boolean isEmpty() {
        return transactions.size() == 0;
    }

    @Override
    public void remove(Transaction expense) {
        total = expense.subtractFrom( total );
        transactions.remove( expense );
    }

    @Override
    public BigDecimal getAverage() {
        return total.divide( BigDecimal.valueOf( transactions.size() ), 2, RoundingMode.HALF_UP );
    }

    @Override
    public List<String> getTransactionDescriptions() {
        return transactions.stream().map(expense -> expense.getDescriptionStringRepresentation()).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public class InvalidTransactionValueException extends RuntimeException {
        public InvalidTransactionValueException(String message) {
            super( message );
        }
    }
}
