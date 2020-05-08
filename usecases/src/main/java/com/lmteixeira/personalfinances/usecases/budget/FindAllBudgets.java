package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;

import java.util.List;

public class FindAllBudgets {

    private BudgetRepository budgetRepository;

    public FindAllBudgets(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<Budget> findAllBudgets() {
        return budgetRepository.findAllBudgets();
    }
}
