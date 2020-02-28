package com.lmteixeira.personalfinances.budget;

import com.lmteixeira.personalfinances.expenses.Expense;
import com.lmteixeira.personalfinances.expenses.ExpenseAccount;

public class Budget {

    ExpenseAccount foreseenExpenses = new ExpenseAccount();

    public Long getForeseenExpensesCount() {
        return foreseenExpenses.getExpenseCount();
    }

    public void addForeseenExpense(Expense expense) {
        foreseenExpenses.addExpense( expense );
    }
}
