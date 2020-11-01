package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.AddIncome;
import com.lmteixeira.personalfinances.usecases.budget.GetAllIncome;
import com.lmteixeira.personalfinances.usecases.budget.GetIncomeDescriptions;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.TransactionModel;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeController {

    private AddIncome addIncome;
    private GetIncomeDescriptions getIncomeDescriptions;
    private GetAllIncome getAllIncome;

    public IncomeController(AddIncome addIncome, GetIncomeDescriptions getIncomeDescriptions, GetAllIncome getAllIncome) {
        this.addIncome = addIncome;
        this.getIncomeDescriptions = getIncomeDescriptions;
        this.getAllIncome = getAllIncome;
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

    public List<TransactionWeb> getAllIncome(String userEmail) throws BudgetWebNotFoundException {
        try {
            List<TransactionModel> expenseModels = getAllIncome.getAllIncome(userEmail);
            return expenseModels.stream().map(TransactionWeb::fromModel).collect(Collectors.toList());
        } catch (BudgetNotFoundException exception) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }
}
