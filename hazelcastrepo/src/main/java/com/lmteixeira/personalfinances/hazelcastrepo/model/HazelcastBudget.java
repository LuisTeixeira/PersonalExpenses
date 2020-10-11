package com.lmteixeira.personalfinances.hazelcastrepo.model;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;

import java.io.Serializable;

public class HazelcastBudget implements Serializable {

    private static final long serialVerisionUID = 1L;

    private HazelcastTransactionAccount expenses;
    private HazelcastTransactionAccount income;

    public static HazelcastBudget fromBudget(Budget budget) {
        HazelcastBudget hazelcastBudget = new HazelcastBudget();
        hazelcastBudget.expenses = HazelcastTransactionAccount.fromTransactionAccount(budget.getForeseenExpenses());
        hazelcastBudget.income = HazelcastTransactionAccount.fromTransactionAccount(budget.getForeseenIncome());
        return hazelcastBudget;
    }

    public Budget toBudget() {
        Budget budget = new BudgetImpl();
        expenses.getHazelcastTransactions().stream().forEach(hazelcastExpense -> budget.addForeseenExpense(hazelcastExpense.toTransaction()));
        income.getHazelcastTransactions().stream().forEach(hazelcastIncome -> budget.addForeseenIncome(hazelcastIncome.toTransaction()));
        return budget;
    }

}
