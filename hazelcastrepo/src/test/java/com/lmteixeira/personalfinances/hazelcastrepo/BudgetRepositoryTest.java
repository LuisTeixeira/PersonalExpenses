package com.lmteixeira.personalfinances.hazelcastrepo;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
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
            Budget budget = budgetRepository.findBudgetByUserEmail( userEmail );
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
        TransactionFactory transactionFactory = new TransactionFactory();
        for ( int i = 0; i < 3; i++ ) {
            budget.addForeseenExpense( transactionFactory.createTransaction( BigDecimal.valueOf( 0 ), "Test", 0L ) );
        }
        budgetRepository.save(userEmail , budget );
        Long expensesCount = budgetRepository.getExpensesCount( userEmail );
        Assert.assertEquals( Long.valueOf( expensesAdded), expensesCount );
    }
}
