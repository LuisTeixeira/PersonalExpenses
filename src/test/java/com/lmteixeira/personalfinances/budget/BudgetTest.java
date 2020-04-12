package com.lmteixeira.personalfinances.budget;

import com.lmteixeira.personalfinances.transaction.Transaction;
import com.lmteixeira.personalfinances.transaction.TransactionFactory;
import com.lmteixeira.personalfinances.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetTest {

    private Budget budget;
    private TransactionFactory transactionFactory;

    @Before
    public void setup() {
        budget = new Budget();
        transactionFactory = new TransactionFactory();
    }

    @Test
    public void newBudgetShouldHaveZeroForeseenExpenses() {
        Long foreseenExpensesCount = budget.getForeseenExpensesCount();
        Assert.assertEquals( Long.valueOf( 0 ), foreseenExpensesCount );
    }

    @Test
    public void afterAddingOneForeseenExpenseCountShouldBeEqualToOne() {
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        addForeseenExpense( expense );
        Long foreseenExpensesCount = budget.getForeseenExpensesCount();
        Assert.assertEquals( Long.valueOf( 1 ), foreseenExpensesCount );
    }

    @Test
    public void totalOfForeseenExpensesAfterAddingOneExpenseShouldBeEqualToForeseenExpense() {
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        addForeseenExpense( expense );
        BigDecimal total = budget.getForeseenExpensesTotal();
        Assert.assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.valueOf( 22.5d ), total ) );
    }

    @Test
    public void newBudgetShouldHaveZeroForeseenIncome() {
        Long foreseenIncomeCount = budget.getForeseenIncomeCount();
        Assert.assertEquals( Long.valueOf( 0 ), foreseenIncomeCount );
    }

    @Test
    public void afterAddingOneForeseenIncomeCountShouldBeEqualToOne() {
        Transaction income = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        budget.addForeseenIncome( income );
        Long foreseenIncomeCount = budget.getForeseenIncomeCount();
        Assert.assertEquals( Long.valueOf( 1 ), foreseenIncomeCount );
    }

    @Test
    public void totalOfForeseenIncomeAfterAddingOneExpenseShouldBeEqualToForeseenIncome() {
        Transaction income = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        budget.addForeseenIncome( income );
        BigDecimal total = budget.getForeseenIncomeTotal();
        Assert.assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.valueOf( 22.5d ), total ) );
    }

    private void addForeseenExpense(Transaction expense) {
        this.budget.addForeseenExpense( expense );
    }

}
