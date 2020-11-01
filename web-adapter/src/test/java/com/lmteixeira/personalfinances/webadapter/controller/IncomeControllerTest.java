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

public class IncomeControllerTest {

    private static final String USER_EMAIL = "test@test.com";

    private TestConfig config;
    private UserController userController;
    private IncomeController incomeController;

    @Before
    public void setup() throws UserNotFoundWebException {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.incomeController = config.incomeController();
        createUser();
    }

    private void createUser() throws UserNotFoundWebException {
        userController.createUser(new UserWeb(USER_EMAIL));
    }

    @Test
    public void getAllIncomeDescriptionsShouldReturnAnEmptyListWhenNoIncomeWasAdded() throws BudgetWebNotFoundException {
        List<String> incomeDescriptions = incomeController.getIncomeDescriptions(USER_EMAIL);
        Assert.assertEquals(0, incomeDescriptions.size());
    }

    @Test
    public void getAllIncomeDescriptionsShouldThrowExceptionWhenBudgetDoesNotExist() {
        String notCreatedUserEmail = "not_created@user.com";
        boolean exceptionThrown = false;
        try {
            List<String> incomeDescriptions = incomeController.getIncomeDescriptions(notCreatedUserEmail);
        } catch (BudgetWebNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getAllIncomeDescriptionsShouldReturnAListWithTheSameNumberOfElementsAsIncomeAdded() throws BudgetWebNotFoundException {
        TransactionWeb transactionWeb = new TransactionWeb("Test Income", 1000d);
        incomeController.add(USER_EMAIL, transactionWeb);
        List<String> incomeDescriptions = incomeController.getIncomeDescriptions(USER_EMAIL);
        Assert.assertEquals(1, incomeDescriptions.size());
    }

    @Test
    public void addIncomeShouldThrowExceptionWhenBudgetDoesNotExist() {
        String notCreatedUserEmail = "not_created@user.com";
        TransactionWeb transactionWeb = new TransactionWeb("Test Expense", 1000d);
        boolean exceptionThrown = false;
        try {
            incomeController.add(notCreatedUserEmail, transactionWeb);
        } catch (BudgetWebNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getAllIncomeShouldReturnAnEmptyListWhenNoExpensesWereAdded() throws BudgetWebNotFoundException {
        List<TransactionWeb> expenses = incomeController.getAllIncome(USER_EMAIL);
        Assert.assertEquals(0, expenses.size());
    }

    @Test
    public void getAllIncomeShouldThrowExceptionWhenBudgetDoesNotExist() {
        String notCreatedUserEmail = "not_created@user.com";
        boolean exceptionThrown = false;
        try {
            List<TransactionWeb> expensesDescriptions = incomeController.getAllIncome(notCreatedUserEmail);
        } catch (BudgetWebNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getAllIncomeShouldReturnAListWithTheSameNumberOfElementsAsExpensesAdded() throws BudgetWebNotFoundException {
        TransactionWeb transactionWeb = new TransactionWeb("Test Expense", 20.0d);
        incomeController.add(USER_EMAIL, transactionWeb);
        List<TransactionWeb> expensesDescriptions = incomeController.getAllIncome(USER_EMAIL);
        Assert.assertEquals(1, expensesDescriptions.size());
    }
}
