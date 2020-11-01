package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import com.lmteixeira.personalfinances.usecases.models.TransactionModel;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllIncome {

    private BudgetRepository budgetRepository;

    public GetAllIncome(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<TransactionModel> getAllIncome(String userEmail) throws BudgetNotFoundException {
        Budget budget = null;
        try {
            budget = budgetRepository.findBudgetByUserEmail(userEmail);
        } catch (EntityNotFoundException exception) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
        List<TransactionModel> incomes = budget.getForeseenIncome().getTransactions().stream().map(TransactionModel::fromTransaction).collect(Collectors.toList());
        return incomes;
    }
}
