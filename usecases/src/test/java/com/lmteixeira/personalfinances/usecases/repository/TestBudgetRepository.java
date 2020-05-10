package com.lmteixeira.personalfinances.usecases.repository;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBudgetRepository implements BudgetRepository {

    private Map<String, Budget> budgets = new HashMap<>();

    @Override
    public void create(String userEmail, Budget budget) {
        budgets.put(userEmail, budget);
    }

    @Override
    public List<Budget> findAllBudgets() {
        return new ArrayList<>(budgets.values());
    }

    @Override
    public Budget findBudgetByUserEmail(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget;
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }

    @Override
    public Long findAllBudgetsExpenses(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenExpensesCount();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }
}
