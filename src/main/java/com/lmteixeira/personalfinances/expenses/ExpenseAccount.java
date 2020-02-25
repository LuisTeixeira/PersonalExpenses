package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpenseAccount {

    private BigDecimal total = BigDecimal.valueOf(0);
    private Long expenseCount = 0L;

    public BigDecimal getTotal() {
        return total;
    }

    public void addExpense(Expense expense) {
        if (expense.isLessThanZero()) {
            throw new InvalidExpenseValueException("Value " + expense.getValueStringRepresentation() + " is less than zero");
        }
        total = expense.sum(total);
        expenseCount++;
    }

    public Long getExpenseCount() {
        return expenseCount;
    }

    public void remove(Expense expense) {
        total = expense.subtractFrom(total);
        expenseCount--;
    }

    public BigDecimal getAverage() {
        return total.divide(BigDecimal.valueOf(expenseCount), 2, RoundingMode.HALF_UP);
    }

    public class InvalidExpenseValueException extends RuntimeException {
        public InvalidExpenseValueException(String message) {
            super(message);
        }
    }
}
