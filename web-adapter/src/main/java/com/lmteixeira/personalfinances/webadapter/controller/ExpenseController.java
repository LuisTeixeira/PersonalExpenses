package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.AddExpense;
import com.lmteixeira.personalfinances.usecases.budget.GetAllExpenses;
import com.lmteixeira.personalfinances.usecases.budget.GetExpenseDescriptions;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.TransactionModel;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseController {

    AddExpense addExpense;
    GetExpenseDescriptions getExpenseDescriptions;
    GetAllExpenses getAllExpenses;

    public ExpenseController(AddExpense addExpense, GetExpenseDescriptions getExpenseDescriptions, GetAllExpenses getAllExpenses) {
        this.addExpense = addExpense;
        this.getExpenseDescriptions = getExpenseDescriptions;
        this.getAllExpenses = getAllExpenses;
    }

    public List<String> getExpenseDescriptions(String userEmail) throws BudgetWebNotFoundException {
        try {
            return getExpenseDescriptions.getExpenseDescriptions(userEmail);
        } catch (BudgetNotFoundException e) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }

    public void add(String userEmail, TransactionWeb expenseWeb) throws BudgetWebNotFoundException {
        try {
            addExpense.addExpense(userEmail, expenseWeb.getDescription(), expenseWeb.getValue());
        } catch (BudgetNotFoundException e) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }

    public List<TransactionWeb> getAllExpenses(String userEmail) throws BudgetWebNotFoundException {
        try {
            List<TransactionModel> expenseModels = getAllExpenses.getAllExpenses(userEmail);
            return expenseModels.stream().map(TransactionWeb::fromModel).collect(Collectors.toList());
        } catch (BudgetNotFoundException exception) {
            throw new BudgetWebNotFoundException("Budget not found for user " + userEmail);
        }
    }
}
