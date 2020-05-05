package com.lmteixeira.personalfinances.domain.account;

import com.lmteixeira.personalfinances.domain.account.factory.TransactionAccountFactory;
import com.lmteixeira.personalfinances.domain.account.impl.TransactionAccountImpl;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TransactionAccountTest {

    private TransactionAccount expenseAccount;
    private TransactionFactory transactionFactory;

    @Before
    public void setup() {
        expenseAccount = new TransactionAccountFactory().createExpenseAccount();
        transactionFactory = new TransactionFactory();
    }

    @Test
    public void newAccountTotalIsZero() {
        BigDecimal total = expenseAccount.getTotal();
        assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.valueOf( 0 ), total ) );
    }

    @Test
    public void newAccountShouldHaveZeroExpenses() {
        Long expensesCount = expenseAccount.getTransactionCount();
        Assert.assertEquals( Long.valueOf( 0 ), expensesCount );
    }

    @Test
    public void totalAfterAddingOneExpenseShouldBeEqualToExpense() {
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        addExpense( expense );
        BigDecimal total = expenseAccount.getTotal();
        assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.valueOf( 22.5d ), total ) );
    }

    @Test
    public void afterAddingOneExpenseCountShouldBeEqualToOne() {
        Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        addExpense( expense );
        Long expensesCount = expenseAccount.getTransactionCount();
        Assert.assertEquals( Long.valueOf( 1 ), expensesCount );
    }

    @Test
    public void afterAddingTwoExpensesTheTotalShouldBeSumOfExpenses() {
        Transaction firstExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        Transaction secondExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 30 ), "Test Description", new Date().getTime() );
        addExpense( firstExpense );
        addExpense( secondExpense );
        BigDecimal total = expenseAccount.getTotal();
        assertTrue( BigDecimalsUtilities.compareBigDecimals( firstExpense.sum( secondExpense ), total ) );
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneTotalShouldBeEqualToKeptExpense() {
        Transaction firstExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        Transaction secondExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 30 ), "Test Description", new Date().getTime() );
        addExpense( firstExpense );
        addExpense( secondExpense );
        expenseAccount.remove( firstExpense );
        assertTrue( secondExpense.isEqualTo( expenseAccount.getTotal() ) );
    }

    @Test
    public void afterAddingTwoExpensesAndRemovingOneCountShouldBeEqualToOne() {
        Transaction firstExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        Transaction secondExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 30 ), "Test Description", new Date().getTime() );
        addExpense( firstExpense );
        addExpense( secondExpense );
        expenseAccount.remove( firstExpense );
        Assert.assertEquals( Long.valueOf( 1 ), expenseAccount.getTransactionCount() );
    }

    @Test
    public void afterAddThreeExpensesAverageShouldBeAverageOfValueOfThreeExpenses() {
        Transaction firstExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description", new Date().getTime() );
        Transaction secondExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 30 ), "Test Description", new Date().getTime() );
        Transaction thirdExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 114.20 ), "Test Description", new Date().getTime() );
        addExpense( firstExpense );
        addExpense( secondExpense );
        addExpense( thirdExpense );
        BigDecimal expenseAccountAverage = expenseAccount.getAverage();
        BigDecimal expectedAverage = thirdExpense.sum( firstExpense.sum( secondExpense ) ).divide( BigDecimal.valueOf( 3 ), 2, RoundingMode.HALF_UP );
        assertTrue( BigDecimalsUtilities.compareBigDecimals( expectedAverage, expenseAccountAverage ) );
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueAExceptionShouldBeThrown() {
        try {
            Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( -22.5d ), "Test Description", new Date().getTime() );
            addExpense( expense );
            Assert.fail( "Exception not Thrown" );
        } catch ( TransactionAccountImpl.InvalidTransactionValueException e ) {
            assertTrue( "Exception Thrown", true );
        }
    }

    @Test
    public void whenAddingAExpenseWithANegativeValueTheExceptionMessageShouldShownTheValueThatWasAdded() {
        try {
            Transaction expense = transactionFactory.createTransaction( BigDecimal.valueOf( -22.5d ), "Test Description", new Date().getTime() );
            addExpense( expense );
            Assert.fail( "Exception not Thrown" );
        } catch ( TransactionAccountImpl.InvalidTransactionValueException e ) {
            String actualMessage = e.getMessage();
            assertTrue( actualMessage.contains( Double.toString( -22.5d ) ) );
        }
    }

    @Test
    public void getExpensesDescriptionsShouldReturnTheDescriptionOfAllAddedExpenses() {
        Transaction firstExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 22.5d ), "Test Description1", new Date().getTime() );
        addExpense( firstExpense );
        Transaction secondExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 30 ), "Test Description2", new Date().getTime() );
        addExpense( secondExpense );
        Transaction thirdExpense = transactionFactory.createTransaction( BigDecimal.valueOf( 114.20 ), "Test Description3", new Date().getTime() );
        addExpense( thirdExpense );
        List<String> expenseDescriptions = expenseAccount.getTransactionDescriptions();
        assertTrue( expenseDescriptions.containsAll( Arrays.asList( "Test Description1", "Test Description2", "Test Description3" ) ) );
    }

    private void addExpense(Transaction expense) {
        this.expenseAccount.addTransaction( expense );
    }

}
