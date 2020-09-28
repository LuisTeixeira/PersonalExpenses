package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.webadapter.config.TestConfig;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BudgetControllerTest {

    private TestConfig config;
    private BudgetController budgetController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.budgetController = this.config.budgetController();
    }

    @Test
    public void getAllBudgetsForUserShouldReturnEmptyListWhenNoBudgetWasCreated() {
        UserWeb user = new UserWeb("test@test.com");
        List<BudgetWeb> budgets = budgetController.getAllBudgets(user);
        Assert.assertEquals(0, budgets.size());
    }

}
