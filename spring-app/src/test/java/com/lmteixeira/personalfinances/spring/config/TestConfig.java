package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.spring.repository.TestBudgetRepository;
import com.lmteixeira.personalfinances.spring.repository.TestUserRepository;
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

}
