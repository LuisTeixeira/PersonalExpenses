package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

public class GetIncomeCount {

    private BudgetRepository budgetRepository;

    public GetIncomeCount(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Long getIncomeCount(String userEmail) {
        try {
            return budgetRepository.getIncomeCount(userEmail);
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }
}
