package com.lmteixeira.personalfinances.budget;

import com.lmteixeira.personalfinances.expenses.Expense;
import com.lmteixeira.personalfinances.expenses.ExpenseFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetTest {

    private Budget budget;
    private ExpenseFactory expenseFactory;

    @Before
    public void setup() {
        budget = new Budget();
        expenseFactory = new ExpenseFactory();
    }

    @Test
    public void newBudgetShouldHaveZeroForeseenExpenses() {
        Long foreseenExpensesCount = budget.getForeseenExpensesCount();
        Assert.assertEquals( Long.valueOf( 0 ), foreseenExpensesCount );
    }

    @Test
    public void afterAddingOneForeseenExpenseCountShouldBeEqualToOne() {
        Expense expense = expenseFactory.createExpense( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        addForeseenExpense( expense );
        Long foreseenExpensesCount = budget.getForeseenExpensesCount();
        Assert.assertEquals( Long.valueOf( 1 ), foreseenExpensesCount );
    }

    private void addForeseenExpense(Expense expense) {
        this.budget.addForeseenExpense( expense );
    }

}
