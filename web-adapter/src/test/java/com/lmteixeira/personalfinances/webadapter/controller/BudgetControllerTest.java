package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.webadapter.config.TestConfig;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.exception.UserNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.BudgetWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BudgetControllerTest {

    private TestConfig config;
    private BudgetController budgetController;
    private UserController userController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.budgetController = this.config.budgetController();
        this.userController = this.config.userController();
    }

    @Test
    public void getBudgetForUserShouldThrowExceptionWhenUserHasNoBudgetCreated() {
        UserWeb user = new UserWeb("test@test.com");
        boolean exceptionThrown = false;
        try{
            BudgetWeb userBudget = budgetController.getBudgetForUser(user);
        } catch (BudgetNotFoundWebException e) {
            exceptionThrown = true;
        }
        Assert.assertEquals(true, exceptionThrown);
    }

    @Test
    public void getBudgetForUserShouldReturnBudgetIfBudgetWasCreated() throws BudgetNotFoundWebException, UserNotFoundWebException {
        UserWeb user = new UserWeb("test@test.com");
        userController.createUser(user);
        budgetController.createBudgetForUser(user);
        BudgetWeb userBudget = budgetController.getBudgetForUser(user);
        Assert.assertNotNull(userBudget);
    }

}
