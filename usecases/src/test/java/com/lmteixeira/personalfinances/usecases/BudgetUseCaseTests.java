package com.lmteixeira.personalfinances.usecases;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.usecases.budget.*;
import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BudgetUseCaseTests {
    private static final String USER_EMAIL = "test@gmail.com";

    private TestConfig config;
    private FindAllBudgets findAllBudgets;
    private FindUserBudget findUserBudget;
    private CreateBudget createBudget;
    private CreateUser createUser;
    private GetExpensesCount getBudgetExpensesCount;
    private AddExpense addExpense;
    private GetExpenseDescriptions getExpenseDescriptions;

    @Before
    public void setup() {
        config = new TestConfig();
        findAllBudgets = config.findAllBudgets();
        createUser = config.createUser();
        createBudget = config.createBudget();
        findUserBudget = config.findUserBudget();
        getBudgetExpensesCount = config.findAllBudgetExpenses();
        addExpense = config.addExpense();
        getExpenseDescriptions = config.getExpensesDescriptions();
    }

    @Test
    public void findAllBudgetsShouldEmptyListWhenNoBudgetsAreCreated() {
        Assert.assertEquals(0, findAllBudgets.findAllBudgets().size());
    }

    @Test
    public void afterCreatingABudgetFindAllBudgetsShouldReturnAListWithOneElement() {
        createUserAndBudget();
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
        createUserAndBudget();
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
        createUserAndBudget();
        addExpense.addExpense(USER_EMAIL, "Test expense", BigDecimal.valueOf(23d));
        Long budgetExpensesCount = getBudgetExpensesCount.getBudgetExpensesCount(USER_EMAIL);
        Assert.assertEquals(Long.valueOf(1), budgetExpensesCount);
    }

    @Test
    public void addingAnExpenseShouldThrowExceptionWhenThereIsNoBudgetCreatedForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            addExpense.addExpense(USER_EMAIL, "Test expense", BigDecimal.valueOf(23d));
        } catch (BudgetNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
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

    @Test
    public void findAllExpensesInBudgetShouldReturnAListWithDescriptionOfAllExistingExpenses() {
        String[] expenseDescriptions = new String[]{"First Expense", "Second Expense"};
        createUserAndBudget();
        for (String description : expenseDescriptions) {
            addExpense.addExpense(USER_EMAIL, description, BigDecimal.ONE);
        }
        List<String> retrievedDescriptions = getExpenseDescriptions.getExpenseDescriptions(USER_EMAIL);
        for (int i = 0; i < expenseDescriptions.length; i++) {
            Assert.assertEquals(expenseDescriptions[i], retrievedDescriptions.get(i));
        }
    }

    @Test
    public void findAllExpensesShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            List<String> retrievedDescriptions = getExpenseDescriptions.getExpenseDescriptions(USER_EMAIL);
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }


    private void createUserAndBudget() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
    }

}
