package com.lmteixeira.personalfinances.spring.controller;

import com.lmteixeira.personalfinances.spring.config.TestConfig;
import com.lmteixeira.personalfinances.spring.rest.SpringExpenseController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import org.junit.Before;
import org.junit.Test;

public class ExpenseControllerTest
{

    private TestConfig config;
    private SpringUserController userController;
    private SpringExpenseController expenseController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.expenseController = config.expenseController();
    }

    @Test
    public void getAllExpensesShouldReturnIsOk() {
        
    }

}
