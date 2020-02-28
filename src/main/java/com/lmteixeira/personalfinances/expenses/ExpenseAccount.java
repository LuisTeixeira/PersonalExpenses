package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseAccount {

    private BigDecimal total = BigDecimal.valueOf( 0 );
    private List<Expense> expenses = new ArrayList<>();

    public BigDecimal getTotal() {
        return total;
    }

    public void addExpense(Expense expense) {
        if ( expense.isLessThanZero() ) {
            throw new InvalidExpenseValueException( "Value " + expense.getValueStringRepresentation() + " is less than zero" );
        }
        total = expense.sum( total );
        expenses.add( expense );
    }

    public Long getExpenseCount() {
        return Long.valueOf( expenses.size() );
    }

    public void remove(Expense expense) {
        total = expense.subtractFrom( total );
        expenses.remove( expense );
    }

    public BigDecimal getAverage() {
        return total.divide( BigDecimal.valueOf( expenses.size() ), 2, RoundingMode.HALF_UP );
    }

    public List<String> getExpenseDescriptions() {
        return expenses.stream().map( expense -> expense.getDescriptionStringRepresentation() ).collect( Collectors.toList() );
    }

    public class InvalidExpenseValueException extends RuntimeException {
        public InvalidExpenseValueException(String message) {
            super( message );
        }
    }
}
