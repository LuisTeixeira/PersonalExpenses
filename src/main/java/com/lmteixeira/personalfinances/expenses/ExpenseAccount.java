package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpenseAccount {

    private BigDecimal total = BigDecimal.valueOf(0);
    private Long expenseCount = 0L;

    public BigDecimal getTotal() {
        return total;
    }

    public void addExpense(BigDecimal expense) {
        if (expense.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidExpenseValueException("Value " + expense.toPlainString() + " is less than zero");
        }
        total = total.add(expense);
        expenseCount++;
    }

    public Long getExpenseCount() {
        return expenseCount;
    }

    public void remove(BigDecimal expense) {
        total = total.subtract(expense);
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
