package com.lmteixeira.personalfinances.usecases;

import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.FindAllBudgets;
import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BudgetUseCaseTests {
    private static final String USER_EMAIL = "test@gmail.com";

    private TestConfig config;
    private FindAllBudgets findAllBudgets;
    private CreateBudget createBudget;
    private CreateUser createUser;

    @Before
    public void setup() {
        config = new TestConfig();
        findAllBudgets = config.findAllBudgets();
        createUser = config.createUser();
        createBudget = config.createBudget();
    }

    @Test
    public void findAllBudgetsShouldEmptyListWhenNoBudgetsAreCreated() {
        Assert.assertEquals(0, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void afterCreatingABudgetFindAllBudgetsShouldReturnAListWithOneElement() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
        FindAllBudgets findAllBudgets = config.findAllBudgets();
        Assert.assertEquals(1, findAllBudgets.findAllBudgets().size());
    }

}
