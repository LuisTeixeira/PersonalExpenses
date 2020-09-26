package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BudgetIncomeUseCases {

    private TestConfig config;
    private BudgetTestUtils utils;
    private AddIncome addIncome;
    private GetIncomeCount getIncomeCount;
    private GetIncomeDescriptions getIncomeDescriptions;
    private GetIncomeTotal getIncomeTotal;

    @Before
    public void setup() {
        config = new TestConfig();
        addIncome = config.addIncome();
        getIncomeCount = config.getIncomeCount();
        getIncomeDescriptions = config.getIncomeDescriptions();
        getIncomeTotal = config.getIncomeTotal();
        utils = new BudgetTestUtils(config);
    }

    @Test
    public void afterAddingIncomeToTheBudgetGetBudgetIncomeCountShouldReturnAListContainingTheAddedIncome() throws UserNotFoundException {
        utils.createUserAndBudget();
        addIncome.addIncome(utils.getUserEmail(), "Test Income", BigDecimal.valueOf(23d));
        Long incomeCount = getIncomeCount.getIncomeCount(utils.getUserEmail());
        Assert.assertEquals(Long.valueOf(1), incomeCount);
    }

    @Test
    public void addingIncomeShouldThrowExceptionWhenThereIsNoBudgetCreatedForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            addIncome.addIncome(utils.getUserEmail(), "Test Income", BigDecimal.valueOf(20d));
        } catch (BudgetNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getIncomeCountShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            Long count = getIncomeCount.getIncomeCount(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getIncomeDescriptionsShouldReturnAListWithDescriptionOfAllExistingIncome() throws UserNotFoundException {
        String[] incomeDescriptions = new String[]{"First Income", "Second Income", "Third Income"};
        utils.createUserAndBudget();
        for (String description : incomeDescriptions) {
            addIncome.addIncome(utils.getUserEmail(), description, BigDecimal.ONE);
        }
        List<String> retrievedDescriptions = getIncomeDescriptions.getIncomeDescriptions(utils.getUserEmail());
        for (int i = 0; i < incomeDescriptions.length; i++) {
            Assert.assertEquals(incomeDescriptions[i], retrievedDescriptions.get(i));
        }
    }

    @Test
    public void getIncomeDescriptionsShouldThrowAnExceptionWhenThereIsNoBudgetForUserWithSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            List<String> retrievedDescriptions = getIncomeDescriptions.getIncomeDescriptions(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void afterAddingAnIncomeToTheBudgetIncomeTotalShouldReturnTheValueOfTheAddedIncome() throws UserNotFoundException {
        utils.createUserAndBudget();
        addIncome.addIncome(utils.getUserEmail(), "Test Income", BigDecimal.valueOf(32d));
        BigDecimal total = getIncomeTotal.getTotal(utils.getUserEmail());
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(BigDecimal.valueOf(32d), total));
    }

    @Test
    public void getBudgetTotalShouldThrowAnExceptionWhenThereIsNoBudgetForUserWIthSpecifiedEmail() {
        boolean exceptionThrown = false;
        try {
            BigDecimal total = getIncomeTotal.getTotal(utils.getUserEmail());
        } catch (BudgetNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }
}
