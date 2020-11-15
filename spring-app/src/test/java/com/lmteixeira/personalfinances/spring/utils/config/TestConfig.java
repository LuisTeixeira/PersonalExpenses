package com.lmteixeira.personalfinances.spring.utils.config;

import com.lmteixeira.personalfinances.spring.utils.repository.TestBudgetRepository;
import com.lmteixeira.personalfinances.spring.utils.repository.TestUserRepository;
import com.lmteixeira.personalfinances.spring.rest.SpringExpenseController;
import com.lmteixeira.personalfinances.spring.rest.SpringIncomeController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import com.lmteixeira.personalfinances.usecases.interfaces.BudgetRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.webadapter.config.SpringConfig;

public class TestConfig {

    private final UserRepository userRepository = new TestUserRepository();
    private final BudgetRepository budgetRepository = new TestBudgetRepository();
    private final SpringConfig springConfig = new SpringConfig(userRepository, budgetRepository);

    public SpringUserController userController() {
        return new SpringUserController(springConfig.userController());
    }

    public SpringExpenseController expenseController() {
        return new SpringExpenseController(springConfig.expenseController());
    }

    public SpringIncomeController incomeController() {
        return new SpringIncomeController(springConfig.incomeController());
    }
}
