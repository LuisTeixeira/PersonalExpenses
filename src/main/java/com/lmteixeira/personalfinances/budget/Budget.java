package com.lmteixeira.personalfinances.budget;

import com.lmteixeira.personalfinances.transaction.Transaction;

import java.math.BigDecimal;

public interface Budget {
    Long getForeseenExpensesCount();

    void addForeseenExpense(Transaction expense);

    BigDecimal getForeseenExpensesTotal();

    void addForeseenIncome(Transaction income);

    Long getForeseenIncomeCount();

    BigDecimal getForeseenIncomeTotal();
}
