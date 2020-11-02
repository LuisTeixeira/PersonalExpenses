package com.lmteixeira.personalfinances.spring.rest;

import com.lmteixeira.personalfinances.webadapter.controller.ExpenseController;

public class SpringExpenseController {

    private ExpenseController expenseController;

    public SpringExpenseController(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

}
