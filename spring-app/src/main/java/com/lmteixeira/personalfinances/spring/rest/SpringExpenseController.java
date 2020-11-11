package com.lmteixeira.personalfinances.spring.rest;

import com.lmteixeira.personalfinances.webadapter.controller.ExpenseController;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringExpenseController {

    private ExpenseController expenseController;

    @Autowired
    public SpringExpenseController(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    public ResponseEntity getAllExpenses(String userEmail) {
        try {
            List<TransactionWeb> expenses = expenseController.getAllExpenses(userEmail);
            return ResponseEntity.status(200).body(expenses);
        } catch (BudgetWebNotFoundException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity add(String userEmail, TransactionWeb expenseWeb) {
        try {
            expenseController.add(userEmail, expenseWeb);
            return ResponseEntity.status(201).build();
        } catch (BudgetWebNotFoundException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
