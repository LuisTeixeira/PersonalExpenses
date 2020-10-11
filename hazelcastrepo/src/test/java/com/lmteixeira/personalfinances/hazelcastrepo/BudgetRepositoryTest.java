package com.lmteixeira.personalfinances.hazelcastrepo;

import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.domain.budget.impl.BudgetImpl;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BudgetRepositoryTest {

    private BudgetRepository budgetRepository;

    @Before
    public void setup() {
        this.budgetRepository = new HazelcastBudgetRepository();
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

}
