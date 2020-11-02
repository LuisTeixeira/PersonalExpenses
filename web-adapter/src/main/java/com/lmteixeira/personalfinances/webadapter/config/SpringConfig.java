package com.lmteixeira.personalfinances.webadapter.config;

import com.lmteixeira.personalfinances.usecases.budget.AddExpense;
import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.budget.GetAllExpenses;
import com.lmteixeira.personalfinances.usecases.budget.GetExpenseDescriptions;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.controller.ExpenseController;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;

public class SpringConfig {

    private UserRepository userRepository;
    private BudgetRepository budgetRepository;

    public SpringConfig(UserRepository userRepository, BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }

    public UserController userController() {
        return new UserController(createUser(), findAllUsers(), findUserByEmail());
    }

    public ExpenseController expenseController() {
        return new ExpenseController(addExpense(), getExpenseDescriptions(), getAllExpenses());
    }

    private FindUserByEmail findUserByEmail() {
        return new FindUserByEmail(userRepository);
    }

    private FindAllUsers findAllUsers() {
        return new FindAllUsers(userRepository);
    }

    private CreateUser createUser() {
        return new CreateUser(userRepository, createBudget());
    }

    private CreateBudget createBudget() {
        return new CreateBudget(userRepository, budgetRepository);
    }

    private AddExpense addExpense() {
        return new AddExpense(budgetRepository);
    }

    private GetExpenseDescriptions getExpenseDescriptions() {
        return new GetExpenseDescriptions(budgetRepository);
    }

    private GetAllExpenses getAllExpenses() {
        return new GetAllExpenses(budgetRepository);
    }
}
