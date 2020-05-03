package com.lmteixeira.personalfinances.expenses;

import com.lmteixeira.personalfinances.transaction.Transaction;
import com.lmteixeira.personalfinances.transaction.impl.TransactionImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAccount {

    private BigDecimal total = BigDecimal.valueOf( 0 );
    private List<Transaction> expenses = new ArrayList<>();

    public BigDecimal getTotal() {
        return total;
    }

    public void addTransaction(Transaction expense) {
        if ( expense.isLessThanZero() ) {
            throw new InvalidTransactionValueException( "Value " + expense.getValueStringRepresentation() + " is less than zero" );
        }
        total = expense.sum( total );
        expenses.add( expense );
    }

    public Long getTransactionCount() {
        return Long.valueOf( expenses.size() );
    }

    public void remove(Transaction expense) {
        total = expense.subtractFrom( total );
        expenses.remove( expense );
    }

    public BigDecimal getAverage() {
        return total.divide( BigDecimal.valueOf( expenses.size() ), 2, RoundingMode.HALF_UP );
    }


    public List<String> getTransactionDescriptions() {
        return expenses.stream().map(expense -> expense.getDescriptionStringRepresentation()).collect(Collectors.toList());
    }

    public class InvalidTransactionValueException extends RuntimeException {
        public InvalidTransactionValueException(String message) {
            super( message );
        }
    }
}
