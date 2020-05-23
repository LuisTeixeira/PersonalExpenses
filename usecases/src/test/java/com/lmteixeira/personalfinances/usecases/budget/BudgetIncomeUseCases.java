package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.BudgetNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BudgetIncomeUseCases {

    private TestConfig config;
    private BudgetTestUtils utils;
    private AddIncome addIncome;
    private GetIncomeCount getIncomeCount;

    @Before
    public void setup() {
        config = new TestConfig();
        addIncome = config.addIncome();
        getIncomeCount = config.getIncomeCount();
        utils = new BudgetTestUtils(config);
    }

    @Test
    public void afterAddingIncomeToTheBudgetGetBudgetIncomeCountShouldReturnAListContainingTheAddedIncome() {
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

}
