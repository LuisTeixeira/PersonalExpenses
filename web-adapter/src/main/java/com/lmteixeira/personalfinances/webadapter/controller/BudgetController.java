package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.FindUserBudget;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.BudgetModel;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.exception.UserNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;

public class BudgetController {

    private FindUserBudget findUserBudget;
    private CreateBudget createBudget;

    public BudgetController(FindUserBudget findUserBudget, CreateBudget createBudget) {
        this.findUserBudget = findUserBudget;
        this.createBudget = createBudget;
    }

    public BudgetWeb getBudgetForUser(UserWeb user) throws BudgetNotFoundWebException {
        try {
            BudgetModel budget = findUserBudget.findUserBudget(user.getEmail());
            return BudgetWeb.toBudgetWebModel(budget);
        } catch (BudgetNotFoundException e) {
            throw new BudgetNotFoundWebException("Budget not found for user " + user.getEmail());
        }

    }

    public void createBudgetForUser(UserWeb user) throws UserNotFoundWebException {
        try {
            createBudget.createBudget(user.getEmail());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundWebException("User " + user.getEmail() + " not found");
        }
    }
}
