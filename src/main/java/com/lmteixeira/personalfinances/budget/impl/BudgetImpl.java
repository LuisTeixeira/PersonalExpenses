package com.lmteixeira.personalfinances.budget.impl;

import com.lmteixeira.personalfinances.budget.Budget;
import com.lmteixeira.personalfinances.account.TransactionAccount;
import com.lmteixeira.personalfinances.account.impl.TransactionAccountImpl;
import com.lmteixeira.personalfinances.transaction.Transaction;

import java.math.BigDecimal;

public class BudgetImpl implements Budget {

    TransactionAccount foreseenExpenses = new TransactionAccountImpl();
    TransactionAccount foreseenIncome = new TransactionAccountImpl();

    @Override
    public Long getForeseenExpensesCount() {
        return foreseenExpenses.getTransactionCount();
    }

    @Override
    public void addForeseenExpense(Transaction expense) {
        foreseenExpenses.addTransaction( expense );
    }

    @Override
    public BigDecimal getForeseenExpensesTotal() {
        return foreseenExpenses.getTotal();
    }

    @Override
    public void addForeseenIncome(Transaction income) {
        foreseenIncome.addTransaction( income );
    }

    @Override
    public Long getForeseenIncomeCount() {
        return foreseenIncome.getTransactionCount();
    }

    @Override
    public BigDecimal getForeseenIncomeTotal() {
        return foreseenIncome.getTotal();
    }
}
