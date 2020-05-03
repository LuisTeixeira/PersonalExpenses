package com.lmteixeira.personalfinances.expenses.impl;

import com.lmteixeira.personalfinances.expenses.TransactionAccount;
import com.lmteixeira.personalfinances.transaction.Transaction;
import com.lmteixeira.personalfinances.transaction.impl.TransactionImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAccountImpl implements TransactionAccount {

    private BigDecimal total = BigDecimal.valueOf( 0 );
    private List<Transaction> expenses = new ArrayList<>();

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
        expenses.add( expense );
    }

    @Override
    public Long getTransactionCount() {
        return Long.valueOf( expenses.size() );
    }

    @Override
    public void remove(Transaction expense) {
        total = expense.subtractFrom( total );
        expenses.remove( expense );
    }

    @Override
    public BigDecimal getAverage() {
        return total.divide( BigDecimal.valueOf( expenses.size() ), 2, RoundingMode.HALF_UP );
    }


    @Override
    public List<String> getTransactionDescriptions() {
        return expenses.stream().map(expense -> expense.getDescriptionStringRepresentation()).collect(Collectors.toList());
    }

    public class InvalidTransactionValueException extends RuntimeException {
        public InvalidTransactionValueException(String message) {
            super( message );
        }
    }
}
