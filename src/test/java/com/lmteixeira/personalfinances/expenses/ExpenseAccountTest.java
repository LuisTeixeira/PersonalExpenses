package com.lmteixeira.personalfinances.expenses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpenseAccountTest {

    private ExpenseAccount expenseAccount;

    @Before
    public void setup() {
        expenseAccount = new ExpenseAccount();
    }

    @Test
    public void newAccountTotalIsZero() {
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(BigDecimal.valueOf(0), total));
    }

    @Test
    public void newAccountShouldHaveZeroExpenses() {
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals( Long.valueOf(0), expensesCount);
    }

    @Test
    public void totalAfterAddingOneExpenseShouldBeEqualToExpense() {
        addExpense(BigDecimal.valueOf(22.5d));
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(BigDecimal.valueOf(22.5d), total));
    }

    @Test
    public void afterAddingOneExpenseCountShouldBeEqualToOne() {
        addExpense(BigDecimal.valueOf(22.5d));
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals(Long.valueOf(1), expensesCount);
    }

    @Test
    public void afterAddingTwoExpensesTheTotalShouldBeSumOfExpenses() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        addExpense(firstExpense);
        addExpense(secondExpense);
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(firstExpense.add(secondExpense), total));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneTotalShouldBeEqualToKeptExpense() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        addExpense(firstExpense);
        addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertTrue(compareBigDecimals(secondExpense, expenseAccount.getTotal()));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneCountShouldBeEqualToOne() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        addExpense(firstExpense);
        addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertEquals(Long.valueOf(1), expenseAccount.getExpenseCount());
    }

    @Test
    public void afterAddThreeExpensesAverageShouldBeAverageOfValueOfThreeExpenses() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        BigDecimal thirdExpense = new BigDecimal(114.20);
        addExpense(firstExpense);
        addExpense(secondExpense);
        addExpense(thirdExpense);
        BigDecimal expenseAccountAverage = expenseAccount.getAverage();
        BigDecimal expectedAverage = firstExpense.add(secondExpense).add(thirdExpense).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        Assert.assertTrue(compareBigDecimals(expectedAverage, expenseAccountAverage));
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueAExceptionShouldBeThrown() {
        try {
            addExpense(BigDecimal.valueOf(-23.2));
            Assert.assertTrue("Exception not Thrown", false);
        } catch (ExpenseAccount.InvalidExpenseValueException e) {
            Assert.assertTrue("Exception Thrown", true);
        }
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueTheExceptionMessageShouldShownTheValueThatWasAdded() {
        try {
            addExpense(BigDecimal.valueOf(-23.2));
            Assert.assertTrue("Exception not Thrown", false);
        } catch (ExpenseAccount.InvalidExpenseValueException e) {
            String actualMessage = e.getMessage();
            Assert.assertTrue(actualMessage.contains(Double.toString(-23.2)));
        }
    }

    private void addExpense(BigDecimal expenseValue) {
        this.expenseAccount.addExpense(expenseValue);
    }

    private static boolean compareBigDecimals(BigDecimal expected, BigDecimal actual) {
        return (expected.compareTo(actual) == 0);
    }
}
