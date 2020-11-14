package com.lmteixeira.personalfinances.spring.http;

import com.lmteixeira.personalfinances.spring.rest.SpringExpenseController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpenseControllerHttpRequestTest {

    @Autowired
    private SpringExpenseController expenseController;

    @Test
    public void contextLoadsTest() {
        Assert.assertNotNull(expenseController);
    }

}
