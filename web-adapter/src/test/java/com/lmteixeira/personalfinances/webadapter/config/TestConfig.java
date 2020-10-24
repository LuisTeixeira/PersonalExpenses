package com.lmteixeira.personalfinances.webadapter.config;

import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.FindUserBudget;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.controller.BudgetController;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import com.lmteixeira.personalfinances.webadapter.repository.TestBudgetRepository;
import com.lmteixeira.personalfinances.webadapter.repository.TestUserRepository;

public class TestConfig {

    UserRepository userRepository = new TestUserRepository();
    BudgetRepository budgetRepository = new TestBudgetRepository();

    public UserController userController() {
        CreateBudget createBudget = new CreateBudget(userRepository, budgetRepository);
        CreateUser createUser = new CreateUser(userRepository, createBudget);
        FindAllUsers findAllUsers = new FindAllUsers(userRepository);
        FindUserByEmail findUserByEmail = new FindUserByEmail(userRepository);
        return new UserController(createUser, findAllUsers, findUserByEmail);
    }

    public BudgetController budgetController() {
        FindUserBudget findUserBudget = new FindUserBudget(budgetRepository);
        CreateBudget createBudget = new CreateBudget(userRepository, budgetRepository);
        return new BudgetController(findUserBudget, createBudget);
    }
}
