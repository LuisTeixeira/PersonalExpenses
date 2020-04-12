package com.lmteixeira.personalfinances.budget;

import com.lmteixeira.personalfinances.expenses.TransactionAccount;
import com.lmteixeira.personalfinances.transaction.Transaction;

import java.math.BigDecimal;

public class Budget {

    TransactionAccount foreseenExpenses = new TransactionAccount();
    TransactionAccount foreseenIncome = new TransactionAccount();

    public Long getForeseenExpensesCount() {
        return foreseenExpenses.getTransactionCount();
    }

    public void addForeseenExpense(Transaction expense) {
        foreseenExpenses.addTransaction( expense );
    }

    public BigDecimal getForeseenExpensesTotal() {
        return foreseenExpenses.getTotal();
    }

    public void addForeseenIncome(Transaction income) {
        foreseenIncome.addTransaction( income );
    }

    public Long getForeseenIncomeCount() {
        return foreseenIncome.getTransactionCount();
    }

    public BigDecimal getForeseenIncomeTotal() {
        return foreseenIncome.getTotal();
    }
}
