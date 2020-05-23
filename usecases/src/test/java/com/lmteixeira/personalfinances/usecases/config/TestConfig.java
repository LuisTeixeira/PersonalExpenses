package com.lmteixeira.personalfinances.usecases.config;

import com.lmteixeira.personalfinances.usecases.budget.*;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.config.repository.TestBudgetRepository;
import com.lmteixeira.personalfinances.usecases.config.repository.TestUserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;

public class TestConfig {

    private UserRepository userRepository = new TestUserRepository();
    private BudgetRepository budgetRepository = new TestBudgetRepository();

    public CreateUser createUser() {
        return new CreateUser(userRepository);
    }

    public FindAllUsers findAllUsers() {
        return new FindAllUsers(userRepository);
    }

    public FindAllBudgets findAllBudgets() {
        return new FindAllBudgets(budgetRepository);
    }

    public CreateBudget createBudget() {
        return new CreateBudget(userRepository, budgetRepository);
    }

    public FindUserByEmail findUserByEmail() {
        return new FindUserByEmail(userRepository);
    }

    public FindUserBudget findUserBudget() {
        return new FindUserBudget(budgetRepository);
    }

    public GetExpensesCount getExpensesCount() {
        return new GetExpensesCount(budgetRepository);
    }

    public AddExpense addExpense() {
        return new AddExpense(budgetRepository);
    }

    public GetExpenseDescriptions getExpensesDescriptions() {
        return new GetExpenseDescriptions(budgetRepository);
    }

    public GetExpensesTotal getExpensesTotal() {
        return new GetExpensesTotal(budgetRepository);
    }

    public AddIncome addIncome() {
        return new AddIncome(budgetRepository);
    }

    public GetIncomeCount getIncomeCount() {
        return new GetIncomeCount(budgetRepository);
    }
}
