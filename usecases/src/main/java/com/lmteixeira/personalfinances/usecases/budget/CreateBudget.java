package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

public class CreateBudget {

    UserRepository userRepository;
    BudgetRepository budgetRepository;

    public CreateBudget(UserRepository userRepository, BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }

    public void createBudget(String userEmail) throws UserNotFoundException {

        try {
            userRepository.findUserByEmail(userEmail);
            Budget budget = new BudgetImpl();
            budgetRepository.create(userEmail, budget);
        } catch (EntityNotFoundException ex) {
            throw new UserNotFoundException("Cannot create Budget - User with email " + userEmail + " does not exist");
        }

    }

}
