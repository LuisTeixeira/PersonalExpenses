package com.lmteixeira.personalfinances.usecases.budget;

import com.lmteixeira.personalfinances.usecases.user.CreateUser;

public class BudgetTestUtils {

    private static final String USER_EMAIL = "test@gmail.com";

    private CreateUser createUser;
    private CreateBudget createBudget;

    public BudgetTestUtils(CreateUser createUser, CreateBudget createBudget) {
        this.createUser = createUser;
        this.createBudget = createBudget;
    }

    protected String getUserEmail() {
        return USER_EMAIL;
    }

    protected void createUserAndBudget() {
        createUser.create(USER_EMAIL);
        createBudget.createBudget(USER_EMAIL);
    }
}
