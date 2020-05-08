package com.lmteixeira.personalfinances.usecases.config;

import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.FindAllBudgets;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.repository.TestBudgetRepository;
import com.lmteixeira.personalfinances.usecases.repository.TestUserRepository;
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
}
