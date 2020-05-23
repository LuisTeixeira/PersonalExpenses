package com.lmteixeira.personalfinances.usecases.interfaces;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface BudgetRepository {

    void create(String userEmail, Budget budget);

    List<Budget> findAllBudgets();

    Budget findBudgetByUserEmail(String userEmail) throws EntityNotFoundException;

    Long getExpensesCount(String userEmail) throws EntityNotFoundException;

    void save(Budget budget);

    List<String> getExpenseDescriptions(String userEmail) throws EntityNotFoundException;

    BigDecimal getExpensesTotal(String userEmail) throws EntityNotFoundException;

    Long getIncomeCount(String userEmail) throws EntityNotFoundException;
}
