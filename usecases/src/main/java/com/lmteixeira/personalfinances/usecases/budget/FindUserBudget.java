package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;

public class FindUserBudget {

    public BudgetRepository budgetRepository;

    public FindUserBudget(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget findUserBudget(String userEmail) {
        try {
            return budgetRepository.findBudgetByUserEmail(userEmail);
        } catch (com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
