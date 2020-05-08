package com.lmteixeira.personalfinances.usecases.interfaces;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.user.User;

import java.util.List;

public interface BudgetRepository {

    void create(String userEmail, Budget budget);

    List<Budget> findAllBudgets();
}
