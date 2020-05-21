package com.lmteixeira.personalfinances.usecases.budget;


import com.lmteixeira.personalfinances.usecases.converter.BudgetModelConverter;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.BudgetModel;

public class FindUserBudget {

    public BudgetRepository budgetRepository;

    public FindUserBudget(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public BudgetModel findUserBudget(String userEmail) {
        try {
            return new BudgetModelConverter().convert(budgetRepository.findBudgetByUserEmail(userEmail));
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }

}
