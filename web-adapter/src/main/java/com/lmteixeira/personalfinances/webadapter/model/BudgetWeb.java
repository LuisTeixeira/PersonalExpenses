package com.lmteixeira.personalfinances.webadapter.model;

import com.lmteixeira.personalfinances.usecases.models.BudgetModel;

public class BudgetWeb {

    private String balance;
    private boolean isNegative;

    public static BudgetWeb toBudgetWebModel(BudgetModel budget) {
        BudgetWeb budgetWeb = new BudgetWeb();
        budgetWeb.balance = budget.getBalance().toEngineeringString();
        budgetWeb.isNegative = budget.isNegative();
        return budgetWeb;
    }

    public String getBalance() {
        return balance;
    }

    public boolean isNegative() {
        return isNegative;
    }
}
