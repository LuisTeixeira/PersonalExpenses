package com.lmteixeira.personalfinances.spring.rest;

import com.lmteixeira.personalfinances.webadapter.controller.BudgetController;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import org.springframework.http.ResponseEntity;

public class SpringBudgetController {

    private BudgetController budgetController;

    public SpringBudgetController(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    public ResponseEntity<BudgetWeb> getBudgetForUser(String userEmail) {
        try {
            BudgetWeb budgetWeb = budgetController.getBudgetForUser(userEmail);
            return ResponseEntity.ok(budgetWeb);
        } catch (BudgetNotFoundWebException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
