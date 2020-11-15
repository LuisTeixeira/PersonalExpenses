package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.spring.rest.SpringExpenseController;
import com.lmteixeira.personalfinances.spring.rest.SpringIncomeController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigTest {

    @Autowired
    private SpringUserController userController;
    @Autowired
    private SpringExpenseController expenseController;
    @Autowired
    private SpringIncomeController incomeController;

    @Test
    public void userControllerLoadsTest() {
        Assert.assertNotNull(userController);
    }

    @Test
    public void expenseControllerLoadsTest() {
        Assert.assertNotNull(expenseController);
    }

    @Test
    public void incomeControllerLoadsTest() {
        Assert.assertNotNull(incomeController);
    }
}
