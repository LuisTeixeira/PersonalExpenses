package com.lmteixeira.personalfinances.webadapter.model;

import com.lmteixeira.personalfinances.usecases.models.BudgetModel;

public class BudgetWeb {

public static BudgetWeb toBudgetWebModel(BudgetModel budget) {
    return new BudgetWeb();
}

}
