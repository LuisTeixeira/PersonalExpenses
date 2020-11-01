package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.ExpenseModel;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllExpenses {

    private BudgetRepository budgetRepository;

    public GetAllExpenses(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<ExpenseModel> getAllExpenses(String userEmail) throws BudgetNotFoundException {
        Budget budget = null;
        try {
            budget = budgetRepository.findBudgetByUserEmail(userEmail);
        } catch (EntityNotFoundException exception) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
        List<ExpenseModel> expenses = budget.getForeseenExpenses().getTransactions().stream().map(ExpenseModel::fromTransaction).collect(Collectors.toList());
        return expenses;
    }
}
