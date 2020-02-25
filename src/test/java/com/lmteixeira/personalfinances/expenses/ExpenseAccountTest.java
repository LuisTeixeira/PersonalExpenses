package com.lmteixeira.personalfinances.expenses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

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
        Expense expense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        addExpense(expense);
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(BigDecimal.valueOf(22.5d), total));
    }

    @Test
    public void afterAddingOneExpenseCountShouldBeEqualToOne() {
        Expense expense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        addExpense(expense);
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals(Long.valueOf(1), expensesCount);
    }

    @Test
    public void afterAddingTwoExpensesTheTotalShouldBeSumOfExpenses() {
        Expense firstExpense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        Expense secondExpense = createExpense(BigDecimal.valueOf(30), "Test Description", new Date().getTime());
        addExpense(firstExpense);
        addExpense(secondExpense);
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertTrue(compareBigDecimals(firstExpense.sum(secondExpense), total));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneTotalShouldBeEqualToKeptExpense() {
        Expense firstExpense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        Expense secondExpense = createExpense(BigDecimal.valueOf(30), "Test Description", new Date().getTime());
        addExpense(firstExpense);
        addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertTrue(secondExpense.isEqualTo(expenseAccount.getTotal()));
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneCountShouldBeEqualToOne() {
        Expense firstExpense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        Expense secondExpense = createExpense(BigDecimal.valueOf(30), "Test Description", new Date().getTime());
        addExpense(firstExpense);
        addExpense(secondExpense);
        expenseAccount.remove(firstExpense);
        Assert.assertEquals(Long.valueOf(1), expenseAccount.getExpenseCount());
    }

    @Test
    public void afterAddThreeExpensesAverageShouldBeAverageOfValueOfThreeExpenses() {
        Expense firstExpense = createExpense(BigDecimal.valueOf(22.5d), "Test Description", new Date().getTime());
        Expense secondExpense = createExpense(BigDecimal.valueOf(30), "Test Description", new Date().getTime());
        Expense thirdExpense = createExpense(BigDecimal.valueOf(114.20), "Test Description", new Date().getTime());
        addExpense(firstExpense);
        addExpense(secondExpense);
        addExpense(thirdExpense);
        BigDecimal expenseAccountAverage = expenseAccount.getAverage();
        BigDecimal expectedAverage = thirdExpense.sum(firstExpense.sum(secondExpense)).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        Assert.assertTrue(compareBigDecimals(expectedAverage, expenseAccountAverage));
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueAExceptionShouldBeThrown() {
        try {
            Expense expense = createExpense(BigDecimal.valueOf(-22.5d), "Test Description", new Date().getTime());
            addExpense(expense);
            Assert.assertTrue("Exception not Thrown", false);
        } catch (ExpenseAccount.InvalidExpenseValueException e) {
            Assert.assertTrue("Exception Thrown", true);
        }
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueTheExceptionMessageShouldShownTheValueThatWasAdded() {
        try {
            Expense expense = createExpense(BigDecimal.valueOf(-22.5d), "Test Description", new Date().getTime());
            addExpense(expense);
            Assert.assertTrue("Exception not Thrown", false);
        } catch (ExpenseAccount.InvalidExpenseValueException e) {
            String actualMessage = e.getMessage();
            Assert.assertTrue(actualMessage.contains(Double.toString(-22.5d)));
        }
    }

    private void addExpense(Expense expense) {
        this.expenseAccount.addExpense(expense);
    }

    private Expense createExpense(BigDecimal expenseValue, String description, Long timestamp) {
        return new Expense(expenseValue, description, timestamp);
    }

    private static boolean compareBigDecimals(BigDecimal expected, BigDecimal actual) {
        return (expected.compareTo(actual) == 0);
    }
}
