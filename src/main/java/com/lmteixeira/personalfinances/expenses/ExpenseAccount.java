package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;

public class ExpenseAccount {

    private BigDecimal total = BigDecimal.valueOf(0);
    private Long expenseCount = 0L;

    public BigDecimal getTotal() {
        return total;
    }

    public void addExpense(BigDecimal expense) {
        total = total.add(expense);
        expenseCount++;
    }

    public Long getExpenseCount() {
        return expenseCount;
    }
}
