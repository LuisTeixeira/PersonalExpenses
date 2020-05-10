package com.lmteixeira.personalfinances.usecases.interfaces;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.List;

public interface BudgetRepository {

    void create(String userEmail, Budget budget);

    List<Budget> findAllBudgets();

    Budget findBudgetByUserEmail(String userEmail) throws EntityNotFoundException;

    Long findAllBudgetsExpenses(String userEmail) throws EntityNotFoundException;
}
