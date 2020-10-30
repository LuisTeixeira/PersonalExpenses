package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.AddIncome;
import com.lmteixeira.personalfinances.usecases.budget.GetIncomeDescriptions;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;

import java.util.ArrayList;
import java.util.List;

public class IncomeController {

    private AddIncome addIncome;
    private GetIncomeDescriptions getIncomeDescriptions;

    public IncomeController(AddIncome addIncome, GetIncomeDescriptions getIncomeDescriptions) {
        this.addIncome = addIncome;
        this.getIncomeDescriptions = getIncomeDescriptions;
    }

    public List<String> getIncomeDescriptions(String userEmail) throws BudgetWebNotFoundException {
        try {
            return getIncomeDescriptions.getIncomeDescriptions(userEmail);
        } catch (BudgetNotFoundException e) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }

    public void add(String userEmail, TransactionWeb incomeWeb) throws BudgetWebNotFoundException {
        try {
            addIncome.addIncome(userEmail, incomeWeb.getDescription(), incomeWeb.getValue());
        } catch (BudgetNotFoundException e) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }
}
