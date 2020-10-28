package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.webadapter.config.TestConfig;
import com.lmteixeira.personalfinances.webadapter.exception.BudgetWebNotFoundException;
import com.lmteixeira.personalfinances.webadapter.exception.UserNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ExpenseControllerTest {

    private static final String USER_EMAIL = "test@test.com";

    private TestConfig config;
    private UserController userController;
    private ExpenseController expenseController;

    @Before
    public void setup() throws UserNotFoundWebException {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.expenseController = config.transactionController();
        createUser();
    }

    private void createUser() throws UserNotFoundWebException {
        userController.createUser(new UserWeb(USER_EMAIL));
    }

    @Test
    public void getAllExpensesDescriptionsShouldReturnAnEmptyListWhenNoExpensesWereAdded() throws BudgetWebNotFoundException {
        List<String> expensesDescriptions = expenseController.getExpenseDescriptions(USER_EMAIL);
        Assert.assertEquals(0, expensesDescriptions.size());
    }

    @Test
    public void getAllExpensesDescriptionsShouldThrowExceptionWhenBudgetDoesNotExist() {
        String notCreatedUserEmail = "not_created@user.com";
        boolean exceptionThrown = false;
        try {
            List<String> expensesDescriptions = expenseController.getExpenseDescriptions(notCreatedUserEmail);
        } catch (BudgetWebNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getAllExpensesDescriptionsShouldReturnAListWithTheSameNumberOfElementsAsExpensesAdded() throws BudgetWebNotFoundException {
        TransactionWeb transactionWeb = new TransactionWeb("Test Expense", 20.0d);
        expenseController.add(USER_EMAIL, transactionWeb);
        List<String> expensesDescriptions = expenseController.getExpenseDescriptions(USER_EMAIL);
        Assert.assertEquals(1, expensesDescriptions.size());
    }

    @Test
    public void addExpenseDescriptionsShouldThrowExceptionWhenBudgetDoesNotExist() {
        String notCreatedUserEmail = "not_created@user.com";
        TransactionWeb transactionWeb = new TransactionWeb("Test Expense", 20.0d);
        boolean exceptionThrown = false;
        try {
            expenseController.add(notCreatedUserEmail, transactionWeb);
        } catch (BudgetWebNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
