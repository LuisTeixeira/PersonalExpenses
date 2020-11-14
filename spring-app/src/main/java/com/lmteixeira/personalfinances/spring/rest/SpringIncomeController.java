package com.lmteixeira.personalfinances.spring.rest;

import com.lmteixeira.personalfinances.webadapter.controller.IncomeController;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringIncomeController {

    private IncomeController incomeController;

    public SpringIncomeController(IncomeController incomeController) {
        this.incomeController = incomeController;
    }

    public ResponseEntity getAllIncome(String userEmail) {
        try {
            List<TransactionWeb> incomeList = incomeController.getAllIncome(userEmail);
            return ResponseEntity.ok(incomeList);
        } catch (BudgetWebNotFoundException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity add(String userEmail, TransactionWeb incomeWeb) {
        try {
            incomeController.add(userEmail, incomeWeb);
            return ResponseEntity.status(201).build();
        } catch (BudgetWebNotFoundException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
