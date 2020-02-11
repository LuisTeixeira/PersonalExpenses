package com.lmteixeira.personalfinances.expenses;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ExpenseAccountTest {

    @Test
    public void newAccountTotalIsZero() {
        ExpenseAccount expenseAccount = new ExpenseAccount();
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertEquals(BigDecimal.valueOf(0), total);
    }

    @Test
    public void newAccountShouldHaveZeroExpenses() {
        ExpenseAccount expenseAccount = new ExpenseAccount();
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals( Long.valueOf(0), expensesCount);
    }

    @Test
    public void totalAfterAddingOneExpenseShouldBeEqualToExpense() {
        ExpenseAccount expenseAccount = new ExpenseAccount();
        expenseAccount.addExpense(BigDecimal.valueOf(22.5d));
        BigDecimal total = expenseAccount.getTotal();
        Assert.assertEquals(BigDecimal.valueOf(22.5d), total);
    }

    @Test
    public void afterAddingOneExpenseCountShouldBeEqualToOne() {
        ExpenseAccount expenseAccount = new ExpenseAccount();
        expenseAccount.addExpense(BigDecimal.valueOf(22.5d));
        Long expensesCount = expenseAccount.getExpenseCount();
        Assert.assertEquals(Long.valueOf(1), expensesCount);
    }

}
