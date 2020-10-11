package com.lmteixeira.personalfinances.webadapter.repository;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;
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
    public Long getExpensesCount(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenExpensesCount();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }

    @Override
    public void save(Budget budget) {
        // Don't need to do anything since this mock repository is a map and only stores a pointer to the object
    }

    @Override
    public List<String> getExpenseDescriptions(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenExpenseDescriptions();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }

    @Override
    public BigDecimal getExpensesTotal(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenExpensesTotal();
        }
        throw new EntityNotFoundException("No budget found for user with email " + userEmail + " found");
    }

    @Override
    public Long getIncomeCount(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenIncomeCount();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }

    @Override
    public List<String> getIncomeDescriptions(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenIncomeDescriptions();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }

    @Override
    public BigDecimal getIncomeTotal(String userEmail) throws EntityNotFoundException {
        Budget budget = budgets.get(userEmail);
        if (budget != null) {
            return budget.getForeseenIncomeTotal();
        }
        throw new EntityNotFoundException("No budget for user with email " + userEmail + " found");
    }
}
