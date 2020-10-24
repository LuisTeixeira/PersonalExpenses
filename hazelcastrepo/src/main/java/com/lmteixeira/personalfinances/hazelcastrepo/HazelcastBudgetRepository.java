package com.lmteixeira.personalfinances.hazelcastrepo;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.lmteixeira.personalfinances.domain.budget.Budget;
import com.lmteixeira.personalfinances.hazelcastrepo.model.HazelcastBudget;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HazelcastBudgetRepository implements BudgetRepository {

    private static final HazelcastInstance HAZELCAST = Hazelcast.getInstance();
    private static final String MAP_NAME = "budget";

    @Override
    public void create(String userEmail, Budget budget) {
        HazelcastBudget hazelcastBudget = HazelcastBudget.fromBudget(budget);
        IMap budgetMap = HAZELCAST.getMap(MAP_NAME);
        budgetMap.put(userEmail, hazelcastBudget);
    }

    @Override
    public List<Budget> findAllBudgets() {
        IMap<String, HazelcastBudget> map = HAZELCAST.getMap(MAP_NAME);
        return map.values().stream().map(HazelcastBudget::toBudget).collect(Collectors.toList());
    }

    @Override
    public Budget findBudgetByUserEmail(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget(userEmail);
        return hazelcastBudget.toBudget();
    }

    @Override
    public Long getExpensesCount(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget(userEmail);
        return hazelcastBudget.toBudget().getForeseenExpensesCount();
    }

    @Override
    public void save(String userEmail, Budget budget) {
        HazelcastBudget hazelcastBudget = HazelcastBudget.fromBudget(budget);
        IMap budgetMap = HAZELCAST.getMap(MAP_NAME);
        budgetMap.put(userEmail, hazelcastBudget);
    }

    @Override
    public List<String> getExpenseDescriptions(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget(userEmail);
        return hazelcastBudget.toBudget().getForeseenExpenseDescriptions();
    }

    @Override
    public BigDecimal getExpensesTotal(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget( userEmail );
        return hazelcastBudget.toBudget().getForeseenExpensesTotal();
    }

    @Override
    public Long getIncomeCount(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget( userEmail );
        return hazelcastBudget.toBudget().getForeseenIncomeCount();
    }

    @Override
    public List<String> getIncomeDescriptions(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget( userEmail );
        return hazelcastBudget.toBudget().getForeseenIncomeDescriptions();
    }

    @Override
    public BigDecimal getIncomeTotal(String userEmail) throws EntityNotFoundException {
        HazelcastBudget hazelcastBudget = getHazelcastBudget( userEmail );
        return hazelcastBudget.toBudget().getForeseenIncomeTotal();
    }

    public void destroy() {
        HAZELCAST.getMap( MAP_NAME ).destroy();
    }

    private HazelcastBudget getHazelcastBudget(String userEmail) throws EntityNotFoundException {
        IMap<String, HazelcastBudget> map = HAZELCAST.getMap( MAP_NAME );
        HazelcastBudget hazelcastBudget = map.get( userEmail );
        if ( hazelcastBudget == null ) {
            throw new EntityNotFoundException( "Budget not found for user" + userEmail );
        }
        return hazelcastBudget;
    }
}
