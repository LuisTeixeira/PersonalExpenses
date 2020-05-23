package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;

public class GetExpensesTotal {

    private BudgetRepository budgetRepository;

    public GetExpensesTotal(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public BigDecimal getTotal(String userEmail) {
        try {
            return budgetRepository.getExpensesTotal(userEmail);
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
