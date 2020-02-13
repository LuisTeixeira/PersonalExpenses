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
        expenseAccount.addExpense(BigDecimal.valueOf(22.5d));
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(BigDecimal.valueOf(22.5d), total));
    }

    @Test
    public void afterAddingOneExpenseCountShouldBeEqualToOne() {
        expenseAccount.addExpense(BigDecimal.valueOf(22.5d));
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals(Long.valueOf(1), expensesCount);
    }

    @Test
    public void afterAddingTwoExpensesTheTotalShouldBeSumOfExpenses() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        expenseAccount.addExpense(firstExpense);
        expenseAccount.addExpense(secondExpense);
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(firstExpense.add(secondExpense), total));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneTotalShouldBeEqualToKeptExpense() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        expenseAccount.addExpense(firstExpense);
        expenseAccount.addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertTrue(compareBigDecimals(secondExpense, expenseAccount.getTotal()));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneCountShouldBeEqualToOne() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        expenseAccount.addExpense(firstExpense);
        expenseAccount.addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertEquals(Long.valueOf(1), expenseAccount.getExpenseCount());
    }

    @Test
    public void afterAddThreeExpensesAverageShouldBeAverageOfValueOfThreeExpenses() {
        BigDecimal firstExpense = new BigDecimal(22.5);
        BigDecimal secondExpense = new BigDecimal(30);
        BigDecimal thirdExpense = new BigDecimal(114.20);
        expenseAccount.addExpense(firstExpense);
        expenseAccount.addExpense(secondExpense);
        expenseAccount.addExpense(thirdExpense);
        BigDecimal expenseAccountAverage = expenseAccount.getAverage();
        BigDecimal expectedAverage = firstExpense.add(secondExpense).add(thirdExpense).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        Assert.assertTrue(compareBigDecimals(expectedAverage, expenseAccountAverage));
    }

    private static boolean compareBigDecimals(BigDecimal expected, BigDecimal actual) {
        return (expected.compareTo(actual) == 0);
    }
}
