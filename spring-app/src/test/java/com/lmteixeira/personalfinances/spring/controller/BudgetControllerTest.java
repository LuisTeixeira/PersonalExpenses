package com.lmteixeira.personalfinances.spring.controller;

import com.lmteixeira.personalfinances.spring.rest.SpringBudgetController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import com.lmteixeira.personalfinances.spring.utils.config.TestConfig;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class BudgetControllerTest {

    private static final String USER_EMAIL = "user@test.com";

    private TestConfig config;
    private SpringUserController userController;
    private SpringBudgetController budgetController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.budgetController = config.budgetController();
        this.userController.createUser(new UserWeb(USER_EMAIL));
    }

    @Test
    public void getBudgetShouldReturnStatusCode200() {
        ResponseEntity<BudgetWeb> budget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertEquals(200, budget.getStatusCodeValue());
    }

    @Test
    public void getBudgetShouldReturnBudgetWithZeroBalanceWhenNoIncomeOrExpensesWereAdded() {
        ResponseEntity<BudgetWeb> budget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertEquals(Double.valueOf(0), budget.getBody().getBalance());
    }

    @Test
    public void getBudgetShouldReturnBudgetThatIsNotNegative() {
        ResponseEntity<BudgetWeb> budget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertFalse(budget.getBody().isNegative());
    }
}
