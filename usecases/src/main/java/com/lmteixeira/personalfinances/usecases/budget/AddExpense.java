package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Date;

public class AddExpense {

    private BudgetRepository budgetRepository;

    public AddExpense(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void addExpense(String userEmail, String description, BigDecimal value) throws BudgetNotFoundException {
        try {
            Budget budget = budgetRepository.findBudgetByUserEmail(userEmail);
            TransactionFactory transactionFactory = new TransactionFactory();
            Transaction expense = transactionFactory.createTransaction(value, description, new Date().getTime());
            budget.addForeseenExpense(expense);
            budgetRepository.save(userEmail, budget );
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }
}
