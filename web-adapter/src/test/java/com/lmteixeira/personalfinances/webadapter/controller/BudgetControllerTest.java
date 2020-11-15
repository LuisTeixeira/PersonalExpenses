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

    private static final String USER_EMAIL = "test@user.com";

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
        UserWeb user = new UserWeb(USER_EMAIL);
        boolean exceptionThrown = false;
        try{
            BudgetWeb userBudget = budgetController.getBudgetForUser(USER_EMAIL);
        } catch (BudgetNotFoundWebException e) {
            exceptionThrown = true;
        }
        Assert.assertEquals(true, exceptionThrown);
    }

    @Test
    public void getBudgetForUserShouldReturnBudgetIfBudgetWasCreated() throws BudgetNotFoundWebException, UserNotFoundWebException {
        UserWeb user = new UserWeb(USER_EMAIL);
        userController.createUser(user);
        budgetController.createBudgetForUser(user);
        BudgetWeb userBudget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertNotNull(userBudget);
    }

    @Test
    public void budgetReturnedAfterCreationShouldHaveBalanceEqualToZero() throws UserNotFoundWebException, BudgetNotFoundWebException {
        UserWeb user = new UserWeb(USER_EMAIL);
        userController.createUser(user);
        budgetController.createBudgetForUser(user);
        BudgetWeb userBudget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertEquals(Double.valueOf(0d), userBudget.getBalance());
    }

    @Test
    public void budgetReturnedAfterCreationShouldNotBeNegative() throws UserNotFoundWebException, BudgetNotFoundWebException {
        UserWeb user = new UserWeb(USER_EMAIL);
        userController.createUser(user);
        budgetController.createBudgetForUser(user);
        BudgetWeb userBudget = budgetController.getBudgetForUser(USER_EMAIL);
        Assert.assertFalse(userBudget.isNegative());
    }

}
