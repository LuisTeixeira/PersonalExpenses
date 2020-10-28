package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.usecases.budget.AddExpense;
import com.lmteixeira.personalfinances.usecases.budget.GetExpenseDescriptions;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;

import java.util.List;

public class ExpenseController {

    AddExpense addExpense;
    GetExpenseDescriptions getExpenseDescriptions;

    public ExpenseController(AddExpense addExpense, GetExpenseDescriptions getExpenseDescriptions) {
        this.addExpense = addExpense;
        this.getExpenseDescriptions = getExpenseDescriptions;
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
}
