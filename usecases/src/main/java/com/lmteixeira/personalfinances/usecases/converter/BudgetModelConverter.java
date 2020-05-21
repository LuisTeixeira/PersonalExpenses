package com.lmteixeira.personalfinances.usecases.converter;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.models.BudgetModel;

import java.util.ArrayList;
import java.util.List;

public class BudgetModelConverter {

    public BudgetModel convert(Budget budget) {
        return new BudgetModel();
    }

    public List<BudgetModel> convertList(List<Budget> budgets) {
        ArrayList<BudgetModel> modelList = new ArrayList<>(budgets.size());
        for (Budget b : budgets) {
            modelList.add(new BudgetModel());
        }
        return modelList;
    }
}
