package com.lmteixeira.personalfinances.expenses;

import java.math.BigDecimal;

public class ExpenseFactory {

    public Expense createExpense(BigDecimal expenseValue, String description, Long timestamp) {
        return new Expense( expenseValue, description, timestamp );
    }

}
