package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BudgetExpensesUseCasesTests {

    private TestConfig config;
    private GetExpensesCount getExpensesCount;
    private AddExpense addExpense;
    private GetExpensesTotal getExpensesTotal;
    private GetExpenseDescriptions getExpenseDescriptions;
    private BudgetTestUtils utils;

    @Before
    public void setup() {
        config = new TestConfig();
        getExpensesCount = config.getExpensesCount();
        addExpense = config.addExpense();
        getExpenseDescriptions = config.getExpensesDescriptions();
        getExpensesTotal = config.getExpensesTotal();
        utils = new BudgetTestUtils(config);
    }

    @Test
    public void afterAddingAnExpenseToTheBudgetGetBudgetExpensesCountShouldReturnAListContainingTheAddedExpense() {
        utils.createUserAndBudget();
        addExpense.addExpense(utils.getUserEmail(), "Test expense", BigDecimal.valueOf(23d));
        Long budgetExpensesCount = getExpensesCount.getBudgetExpensesCount(utils.getUserEmail());
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
            Long count = getExpensesCount.getBudgetExpensesCount(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getExpenseDescriptionsShouldReturnAListWithDescriptionOfAllExistingExpenses() {
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
    public void getExpenseDescriptionsShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            List<String> retrievedDescriptions = getExpenseDescriptions.getExpenseDescriptions(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void afterAddingAnExpenseToTheBudgetGetBudgetExpensesTotalShouldReturnTheValueOfTheAddedExpense() {
        utils.createUserAndBudget();
        addExpense.addExpense(utils.getUserEmail(), "Test Expense", BigDecimal.valueOf(23d));
        BigDecimal total = getExpensesTotal.getTotal(utils.getUserEmail());
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(BigDecimal.valueOf(23d), total));
    }

    @Test
    public void getBudgetExpensesTotalShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            BigDecimal total = getExpensesTotal.getTotal(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
