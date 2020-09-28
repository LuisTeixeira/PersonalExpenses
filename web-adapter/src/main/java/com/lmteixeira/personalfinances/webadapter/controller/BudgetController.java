package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.FindUserBudget;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;

import java.util.ArrayList;
import java.util.List;

public class BudgetController {

    private FindUserBudget findUserBudget;

    public BudgetController(FindUserBudget findUserBudget) {
        this.findUserBudget = findUserBudget;
    }

    public List<BudgetWeb> getAllBudgets(UserWeb user) {
        return new ArrayList<>();
    }
}
