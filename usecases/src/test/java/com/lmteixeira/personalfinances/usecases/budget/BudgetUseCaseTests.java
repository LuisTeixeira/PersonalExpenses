package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.models.BudgetModel;
import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BudgetUseCaseTests {

    private TestConfig config;
    private FindAllBudgets findAllBudgets;
    private FindUserBudget findUserBudget;
    private CreateBudget createBudget;
    private CreateUser createUser;
    private BudgetTestUtils utils;


    @Before
    public void setup() {
        config = new TestConfig();
        findAllBudgets = config.findAllBudgets();
        createUser = config.createUser();
        createBudget = config.createBudget();
        findUserBudget = config.findUserBudget();
        utils = new BudgetTestUtils(config);
    }

    @Test
    public void findAllBudgetsShouldEmptyListWhenNoBudgetsAreCreated() {
        Assert.assertEquals(0, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void afterCreatingABudgetFindAllBudgetsShouldReturnAListWithOneElement() {
        utils.createUserAndBudget();
        Assert.assertEquals(1, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void creatingABudgetForAnNonExistingUserShouldThrowAnException() {
        boolean exceptionThrown = false;
        try {
            createBudget.createBudget(utils.getUserEmail());
        } catch (UserNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void findUserBudgetShouldReturnTheBudgetObjectAssociatedWithTheUserEmailProvided() {
        utils.createUserAndBudget();
        BudgetModel budgetModel = findUserBudget.findUserBudget(utils.getUserEmail());
        Assert.assertNotNull(budgetModel);
    }

    @Test
    public void findUserBudgetShouldReturnABudgetModelWithTheBudgetBalance() {
        utils.createUserAndBudget();
        BudgetModel budgetModel = findUserBudget.findUserBudget(utils.getUserEmail());
        BigDecimal balance = budgetModel.getBalance();
        BigDecimalsUtilities.compareBigDecimals(BigDecimal.valueOf(0L), balance);
    }

    @Test
    public void findUserBudgetShouldReturnABudgetModelWithTheBudgetIsNegative() {
        utils.createUserAndBudget();
        BudgetModel budgetModel = findUserBudget.findUserBudget(utils.getUserEmail());
        boolean isNegative = budgetModel.isNegative();
        Assert.assertFalse(isNegative);
    }

    @Test
    public void findUserBudgetShouldThrowExceptionWhenThereIsNoBudgetCreatedForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            BudgetModel budgetModel = findUserBudget.findUserBudget(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
