package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.converter.BudgetModelConverter;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.models.BudgetModel;

import java.util.List;

public class FindAllBudgets {

    private BudgetRepository budgetRepository;

    public FindAllBudgets(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<BudgetModel> findAllBudgets() {
        return new BudgetModelConverter().convertList(budgetRepository.findAllBudgets());
    }
}
