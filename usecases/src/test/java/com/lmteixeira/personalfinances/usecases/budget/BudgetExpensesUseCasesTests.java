package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BudgetExpensesUseCasesTests {

    private TestConfig config;
    private CreateBudget createBudget;
    private CreateUser createUser;
    private GetExpensesCount getBudgetExpensesCount;
    private AddExpense addExpense;
    private GetExpenseDescriptions getExpenseDescriptions;
    private BudgetTestUtils utils;

    @Before
    public void setup() {
        config = new TestConfig();
        createUser = config.createUser();
        createBudget = config.createBudget();
        getBudgetExpensesCount = config.findAllBudgetExpenses();
        addExpense = config.addExpense();
        getExpenseDescriptions = config.getExpensesDescriptions();
        utils = new BudgetTestUtils(createUser, createBudget);
    }

    @Test
    public void afterAddingAnExpenseToTheBudgetGetBudgetExpensesCountShouldReturnAListContainingTheAddedExpense() {
        utils.createUserAndBudget();
        addExpense.addExpense(utils.getUserEmail(), "Test expense", BigDecimal.valueOf(23d));
        Long budgetExpensesCount = getBudgetExpensesCount.getBudgetExpensesCount(utils.getUserEmail());
        Assert.assertEquals(Long.valueOf(1), budgetExpensesCount);
    }

    @Test
    public void addingAnExpenseShouldThrowExceptionWhenThereIsNoBudgetCreatedForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            addExpense.addExpense(utils.getUserEmail(), "Test expense", BigDecimal.valueOf(23d));
        } catch (BudgetNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getBudgetExpensesCountShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            Long count = getBudgetExpensesCount.getBudgetExpensesCount(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void findAllExpensesInBudgetShouldReturnAListWithDescriptionOfAllExistingExpenses() {
        String[] expenseDescriptions = new String[]{"First Expense", "Second Expense"};
        utils.createUserAndBudget();
        for (String description : expenseDescriptions) {
            addExpense.addExpense(utils.getUserEmail(), description, BigDecimal.ONE);
        }
        List<String> retrievedDescriptions = getExpenseDescriptions.getExpenseDescriptions(utils.getUserEmail());
        for (int i = 0; i < expenseDescriptions.length; i++) {
            Assert.assertEquals(expenseDescriptions[i], retrievedDescriptions.get(i));
        }
    }

    @Test
    public void findAllExpensesShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            List<String> retrievedDescriptions = getExpenseDescriptions.getExpenseDescriptions(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
