package com.lmteixeira.personalfinances.usecases;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.FindUserBudget;
import com.lmteixeira.personalfinances.usecases.budget.GetExpensesCount;
import com.lmteixeira.personalfinances.usecases.budget.FindAllBudgets;
import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetUseCaseTests {
    private static final String USER_EMAIL = "test@gmail.com";

    private TestConfig config;
    private FindAllBudgets findAllBudgets;
    private FindUserBudget findUserBudget;
    private CreateBudget createBudget;
    private CreateUser createUser;
    private GetExpensesCount getBudgetExpensesCount;
    private TransactionFactory transactionFactory;

    @Before
    public void setup() {
        config = new TestConfig();
        findAllBudgets = config.findAllBudgets();
        createUser = config.createUser();
        createBudget = config.createBudget();
        findUserBudget = config.findUserBudget();
        getBudgetExpensesCount = config.findAllBudgetExpenses();
        transactionFactory = new TransactionFactory();
    }

    @Test
    public void findAllBudgetsShouldEmptyListWhenNoBudgetsAreCreated() {
        Assert.assertEquals(0, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void afterCreatingABudgetFindAllBudgetsShouldReturnAListWithOneElement() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
        Assert.assertEquals(1, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void creatingABudgetForAnNonExistingUserShouldThrowAnException() {
        boolean exceptionThrown = false;
        try {
            createBudget.createBudget(USER_EMAIL);
        } catch (UserNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void findUserBudgetShouldReturnTheBudgetObjectAssociatedWithTheUserEmailProvided() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
        Budget budget = findUserBudget.findUserBudget(USER_EMAIL);
        Assert.assertNotNull(budget);
    }

    @Test
    public void findUserBudgetShouldThrowExceptionWhenThereIsNoBudgetCreatedForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            Budget budget = findUserBudget.findUserBudget(USER_EMAIL);
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void afterAddingAnExpenseToTheBudgetGetBudgetExpensesCountShouldReturnAListContainingTheAddedExpense() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
        Budget budget = findUserBudget.findUserBudget(USER_EMAIL);
        Transaction expense = transactionFactory.createTransaction(BigDecimal.valueOf(23d), "Test expense", new Date().getTime());
        budget.addForeseenExpense(expense);
        Long budgetExpensesCount = getBudgetExpensesCount.getBudgetExpensesCount(USER_EMAIL);
        Assert.assertEquals(Long.valueOf(1), budgetExpensesCount);
    }

    @Test
    public void getBudgetExpensesCountShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            Long count = getBudgetExpensesCount.getBudgetExpensesCount(USER_EMAIL);
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
