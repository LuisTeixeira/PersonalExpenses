package com.lmteixeira.personalfinances.hazelcastrepo;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.hazelcastrepo.utilities.BigDecimalsUtilities;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BudgetRepositoryTest {

    private BudgetRepository budgetRepository;

    @Before
    public void setup() {
        this.budgetRepository = new HazelcastBudgetRepository();
    }

    @After
    public void tearDown() {
        ((HazelcastBudgetRepository) budgetRepository).destroy();
    }
    @Test
    public void findAllBudgetsShouldReturnEmptyListWhenNoBudgetWasCreated() {
        List<Budget> budgets = budgetRepository.findAllBudgets();
        Assert.assertEquals(0, budgets.size());
    }

    @Test
    public void findAllBudgetsShouldReturnAListWithTheSameNumberOfBudgetObjectsCreated() {
        String userEmail1 = "test1@test.com";
        String userEmail2 = "test2@test.com";
        Budget budget1 = new BudgetImpl();
        Budget budget2 = new BudgetImpl();
        budgetRepository.create(userEmail1, budget1);
        budgetRepository.create(userEmail2, budget2);
        List<Budget> budgets = budgetRepository.findAllBudgets();
        Assert.assertEquals(2, budgets.size());
    }

    @Test
    public void findBudgetByUserEmailShouldReturnThrowEntityNotFoundExceptionWhenBudgetDoesNotExist(){
        String userEmail = "test@test.com";
        boolean exceptionThrown = false;
        try {
            budgetRepository.findBudgetByUserEmail( userEmail );
        } catch ( EntityNotFoundException e ) {
            exceptionThrown = true;
        }
        Assert.assertTrue( exceptionThrown );
    }

    @Test
    public void findBudgetByUserEmailShouldReturnTheBudgetWhenItWasCreated() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        Budget result = budgetRepository.findBudgetByUserEmail( userEmail );
        Assert.assertNotNull( result );
    }

    // Expenses
    @Test
    public void getExpensesCountShouldReturnZeroWhenNoExpenseWasAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        Long expensesCount = budgetRepository.getExpensesCount( userEmail );
        Assert.assertEquals( Long.valueOf( 0 ), expensesCount );
    }

    @Test
    public void getExpensesCountShouldReturnTheNumberOfExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        int expensesAdded = 3;
        for ( int i = 0; i < 3; i++ ) {
            budget.addForeseenExpense( getTransaction( BigDecimal.valueOf( 0 ), "Test", 0L ) );
        }
        budgetRepository.save(userEmail , budget );
        Long expensesCount = budgetRepository.getExpensesCount( userEmail );
        Assert.assertEquals( Long.valueOf( expensesAdded), expensesCount );
    }

    @Test
    public void getExpensesDescriptionsShouldReturnAnEmptyListWhenNotExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create(userEmail, budget);
        List<String> expensesDescriptions = budgetRepository.getExpenseDescriptions(userEmail);
        Assert.assertEquals(0, expensesDescriptions.size());
    }

    @Test
    public void getExpensesDescriptionsShouldReturnAListWithTheSameNumberOfElementsAsExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budget.addForeseenExpense( getTransaction( BigDecimal.valueOf( 0 ), "Test", 0L ));
        budgetRepository.create(userEmail, budget);
        List<String> expenseDescriptions = budgetRepository.getExpenseDescriptions(userEmail);
        Assert.assertEquals(1, expenseDescriptions.size());
    }

    @Test
    public void getExpensesDescriptionsShouldReturnAListContainingTheDescriptionsOfTheExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        String expenseDescription = "Test";
        Budget budget = new BudgetImpl();
        budget.addForeseenExpense( getTransaction( BigDecimal.valueOf( 0 ), expenseDescription, 0L ));
        budgetRepository.create(userEmail, budget);
        List<String> expenseDescriptions = budgetRepository.getExpenseDescriptions(userEmail);
        Assert.assertEquals(expenseDescription, expenseDescriptions.get(0));
    }

    @Test
    public void getExpensesTotalShouldReturnZeroWhenNoExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        BigDecimal total = budgetRepository.getExpensesTotal( userEmail );
        Assert.assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.ZERO, total) );
    }

    @Test
    public void getExpensesTotalShouldReturnTheValueOfTheExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        String expenseDescription = "Test";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        budget.addForeseenExpense(getTransaction(BigDecimal.valueOf(23d), expenseDescription, 0L));
        budgetRepository.save( userEmail, budget );
        BigDecimal total = budgetRepository.getExpensesTotal(userEmail);
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(BigDecimal.valueOf(23d), total));
    }

    // Income
    @Test
    public void getIncomeCountShouldReturnZeroWhenNoExpenseWasAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        Long incomeCount = budgetRepository.getIncomeCount( userEmail );
        Assert.assertEquals( Long.valueOf( 0 ), incomeCount );
    }

    @Test
    public void getIncomeCountShouldReturnTheNumberOfExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        int incomeAdded = 3;
        for ( int i = 0; i < 3; i++ ) {
            budget.addForeseenIncome( getTransaction( BigDecimal.valueOf( 0 ), "Test", 0L ) );
        }
        budgetRepository.save(userEmail , budget );
        Long incomeCount = budgetRepository.getIncomeCount( userEmail );
        Assert.assertEquals( Long.valueOf( incomeAdded), incomeCount );
    }

    @Test
    public void getIncomeDescriptionsShouldReturnAnEmptyListWhenNotExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create(userEmail, budget);
        List<String> incomeDescriptions = budgetRepository.getIncomeDescriptions(userEmail);
        Assert.assertEquals(0, incomeDescriptions.size());
    }

    @Test
    public void getIncomeDescriptionsShouldReturnAListWithTheSameNumberOfElementsAsExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budget.addForeseenIncome( getTransaction( BigDecimal.valueOf( 0 ), "Test", 0L ));
        budgetRepository.create(userEmail, budget);
        List<String> incomeDescriptions = budgetRepository.getIncomeDescriptions(userEmail);
        Assert.assertEquals(1, incomeDescriptions.size());
    }

    @Test
    public void getIncomeDescriptionsShouldReturnAListContainingTheDescriptionsOfTheExpensesAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        String incomeDescription = "Test";
        Budget budget = new BudgetImpl();
        budget.addForeseenIncome( getTransaction( BigDecimal.valueOf( 0 ), incomeDescription, 0L ));
        budgetRepository.create(userEmail, budget);
        List<String> incomeDescriptions = budgetRepository.getIncomeDescriptions(userEmail);
        Assert.assertEquals(incomeDescription, incomeDescriptions.get(0));
    }

    @Test
    public void getIncomeTotalShouldReturnZeroWhenNoExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        BigDecimal total = budgetRepository.getIncomeTotal( userEmail );
        Assert.assertTrue( BigDecimalsUtilities.compareBigDecimals( BigDecimal.ZERO, total) );
    }

    @Test
    public void getIncomeTotalShouldReturnTheValueOfTheExpensesWereAdded() throws EntityNotFoundException {
        String userEmail = "test@test.com";
        String incomeDescription = "Test";
        Budget budget = new BudgetImpl();
        budgetRepository.create( userEmail, budget );
        budget.addForeseenIncome(getTransaction(BigDecimal.valueOf(23d), incomeDescription, 0L));
        budgetRepository.save( userEmail, budget );
        BigDecimal total = budgetRepository.getIncomeTotal(userEmail);
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(BigDecimal.valueOf(23d), total));
    }

    private Transaction getTransaction(BigDecimal value, String description, Long timestamp) {
        TransactionFactory transactionFactory = new TransactionFactory();
        return transactionFactory.createTransaction(value, description, timestamp);
    }
}
