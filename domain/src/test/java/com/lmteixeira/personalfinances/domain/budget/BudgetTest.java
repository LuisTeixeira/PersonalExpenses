package com.lmteixeira.personalfinances.domain.budget;

import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.domain.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BudgetTest {

    private Budget budget;
    private TransactionFactory transactionFactory;

    @Before
    public void setup() {
        budget = new BudgetImpl();
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

    @Test
    public void whenIncomeValueIsLargerThanExpensesValueBudgetIsNotNegative() {
        Transaction income = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        budget.addForeseenIncome(income);
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 20d ), "Test Description", new Date().getTime() );
        budget.addForeseenExpense(expense);

        Assert.assertFalse(budget.isNegative());
    }

    @Test
    public void whenIncomeValueIsTheSameAsExpensesValueBudgetIsNotNegative() {
        Transaction income = transactionFactory.createTransaction( BigDecimal.valueOf( 20d ), "Test Description", new Date().getTime() );
        budget.addForeseenIncome(income);
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 20d ), "Test Description", new Date().getTime() );
        budget.addForeseenExpense(expense);

        Assert.assertFalse(budget.isNegative());
    }

    @Test
    public void whenIncomeValueIsSmallerThanExpensesValueBudgetIsNegative() {
        Transaction income = transactionFactory.createTransaction( BigDecimal.valueOf( 20d ), "Test Description", new Date().getTime() );
        budget.addForeseenIncome(income);
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        budget.addForeseenExpense(expense);

        Assert.assertTrue(budget.isNegative());
    }

    @Test
    public void afterAddingIncomeAndExpensesBudgetTotalShouldBeTheDifferencePositive() {
        testBudgetTotal(BigDecimal.valueOf(22.5d), BigDecimal.valueOf(20d), BigDecimal.valueOf(2.5d));
    }

    @Test
    public void afterAddingIncomeAndExpensesBudgetTotalShouldBeTheDifferenceZero() {
        testBudgetTotal(BigDecimal.valueOf(20d), BigDecimal.valueOf(20d), BigDecimal.valueOf(0d));
    }

    @Test
    public void afterAddingIncomeAndExpensesBudgetTotalShouldBeTheDifferenceNegative() {
        testBudgetTotal(BigDecimal.valueOf(20d), BigDecimal.valueOf(22.5d), BigDecimal.valueOf(-2.5d));
    }

    @Test
    public void afterAddingForeseenExpensesGetExpensesDescriptionsShouldReturnAListWithTheDescriptions() {
        String[] expenseDescriptions = new String[] {"First Expense", "Second Expense"};
        Transaction firstExpense = transactionFactory.createTransaction(BigDecimal.valueOf(2d), expenseDescriptions[0], new Date().getTime());
        Transaction secondExpense = transactionFactory.createTransaction(BigDecimal.valueOf(2d), expenseDescriptions[1], new Date().getTime());
        budget.addForeseenExpense(firstExpense);
        budget.addForeseenExpense(secondExpense);

        List<String> retrievedExpenseDescriptions = budget.getForeseenExpenseDescriptions();

        for (int i = 0; i < retrievedExpenseDescriptions.size(); i++) {
            Assert.assertEquals(retrievedExpenseDescriptions.get(i), expenseDescriptions[i]);
        }
    }

    @Test
    public void afterAddingForeseenIncomeGetIncomeDescriptionsShouldReturnAListWithTheDescriptions() {
        String[] incomeDescriptions = new String[] {"First Income", "Second Income"};
        Transaction firstIncome = transactionFactory.createTransaction(BigDecimal.valueOf(2d), incomeDescriptions[0], new Date().getTime());
        Transaction secondIncome = transactionFactory.createTransaction(BigDecimal.valueOf(2d), incomeDescriptions[1], new Date().getTime());
        budget.addForeseenIncome(firstIncome);
        budget.addForeseenIncome(secondIncome);

        List<String> retrievedIncomeDescriptions = budget.getForeseenIncomeDescriptions();

        for (int i = 0; i < retrievedIncomeDescriptions.size(); i++) {
            Assert.assertEquals(retrievedIncomeDescriptions.get(i), incomeDescriptions[i]);
        }
    }

    private void testBudgetTotal(BigDecimal incomeValue, BigDecimal expenseValue, BigDecimal expectedValue) {
        Transaction income = transactionFactory.createTransaction( incomeValue, "Test Description", new Date().getTime() );
        budget.addForeseenIncome(income);
        Transaction expense = transactionFactory.createTransaction( expenseValue, "Test Description", new Date().getTime() );
        budget.addForeseenExpense(expense);

        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(budget.total(), expectedValue));
    }

    private void addForeseenExpense(Transaction expense) {
        this.budget.addForeseenExpense( expense );
    }

}
