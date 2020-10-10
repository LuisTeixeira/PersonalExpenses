package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;

public class GetIncomeTotal {

    BudgetRepository budgetRepository;

    public GetIncomeTotal(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public BigDecimal getTotal(String userEmail) throws BudgetNotFoundException {
        try {
            return budgetRepository.getIncomeTotal(userEmail);
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
