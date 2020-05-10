package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

public class GetExpensesCount {

    private BudgetRepository budgetRepository;

    public GetExpensesCount(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Long getBudgetExpensesCount(String userEmail) {
        try {
            return budgetRepository.findAllBudgetsExpenses(userEmail);
        } catch (EntityNotFoundException ex) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
