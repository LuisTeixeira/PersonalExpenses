package com.lmteixeira.personalfinances.domain.budget;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.math.BigDecimal;

public interface Budget {
    Long getForeseenExpensesCount();

    void addForeseenExpense(Transaction expense);

    BigDecimal getForeseenExpensesTotal();

    void addForeseenIncome(Transaction income);

    Long getForeseenIncomeCount();

    BigDecimal getForeseenIncomeTotal();

    boolean isNegative();

    BigDecimal total();
}
