package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.List;

public class GetIncomeDescriptions {

    private BudgetRepository budgetRepository;

    public GetIncomeDescriptions(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<String> getIncomeDescriptions(String userEmail) {
        try {
            return budgetRepository.getIncomeDescriptions(userEmail);
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
