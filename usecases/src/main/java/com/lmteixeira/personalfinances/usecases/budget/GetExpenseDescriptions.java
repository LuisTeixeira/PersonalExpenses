package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.List;

public class GetExpenseDescriptions {

    private BudgetRepository budgetRepository;

    public GetExpenseDescriptions(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<String> getExpenseDescriptions(String userEmail) throws BudgetNotFoundException {
        try {
            return budgetRepository.getExpenseDescriptions(userEmail);
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }
}
