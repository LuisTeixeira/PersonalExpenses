package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;

public class BudgetTestUtils {

    private static final String USER_EMAIL = "test@gmail.com";

    private CreateUser createUser;
    private CreateBudget createBudget;

    public BudgetTestUtils(TestConfig config) {
        this.createUser = config.createUser();
        this.createBudget = config.createBudget();
    }

    protected String getUserEmail() {
        return USER_EMAIL;
    }

    protected void createUserAndBudget() throws UserNotFoundException {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
    }
}
