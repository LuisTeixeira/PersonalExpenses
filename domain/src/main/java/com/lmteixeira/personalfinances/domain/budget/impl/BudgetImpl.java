package com.lmteixeira.personalfinances.domain.budget.impl;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.account.TransactionAccount;
import com.lmteixeira.personalfinances.domain.account.impl.TransactionAccountImpl;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public boolean isNegative() {
        return getForeseenIncomeTotal().compareTo(getForeseenExpensesTotal()) < 0;
    }

    @Override
    public BigDecimal total() {
        return getForeseenIncomeTotal().subtract(getForeseenExpensesTotal());
    }

    @Override
    public List<String> getExpenseDescriptions() {
        return foreseenExpenses.getTransactionDescriptions();
    }

    @Override
    public List<String> getForeseenIncomeDescriptions() {
        return foreseenIncome.getTransactionDescriptions();
    }


}
