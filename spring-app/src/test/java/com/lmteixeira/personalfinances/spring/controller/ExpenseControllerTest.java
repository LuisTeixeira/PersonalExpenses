package com.lmteixeira.personalfinances.spring.controller;

import com.lmteixeira.personalfinances.spring.utils.config.TestConfig;
import com.lmteixeira.personalfinances.spring.rest.SpringExpenseController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ExpenseControllerTest
{

    private static final String USER_EMAIL = "user@test.com";

    private TestConfig config;
    private SpringUserController userController;
    private SpringExpenseController expenseController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.expenseController = config.expenseController();
        this.userController.createUser(new UserWeb(USER_EMAIL));
    }

    @Test
    public void getAllExpensesShouldReturnIsOk() {
        ResponseEntity responseEntity = expenseController.getAllExpenses(USER_EMAIL);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllExpenseShouldReturnAnEmptyListWhenNoExpensesWereAdded() {
        ResponseEntity<List> responseEntity = expenseController.getAllExpenses(USER_EMAIL);
        Assert.assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void getAllExpensesShouldReturnAListWithTheSameNumberOfElementsAsExpensesAdded() {
        TransactionWeb expenseWeb = new TransactionWeb("Test expense", 21.9d);
        expenseController.add(USER_EMAIL, expenseWeb);
        ResponseEntity<List<TransactionWeb>> responseEntity = expenseController.getAllExpenses(USER_EMAIL);
        Assert.assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void addExpenseShouldReturnCode201() {
        TransactionWeb expenseWeb = new TransactionWeb("Test expense", 21.9d);
        ResponseEntity responseEntity = expenseController.add(USER_EMAIL, expenseWeb);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }
    
}
