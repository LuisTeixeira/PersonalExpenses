package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Date;

public class AddIncome {

    private BudgetRepository budgetRepository;

    public AddIncome(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void addIncome(String userEmail, String description, BigDecimal value) throws BudgetNotFoundException {
        try {
            Budget budget = budgetRepository.findBudgetByUserEmail(userEmail);
            TransactionFactory transactionFactory = new TransactionFactory();
            Transaction income = transactionFactory.createTransaction(value, description, new Date().getTime());
            budget.addForeseenIncome(income);
            budgetRepository.save( userEmail, budget );
        } catch (EntityNotFoundException e) {
            throw new BudgetNotFoundException("Budget for user with email " + userEmail + " not found");
        }
    }
}
