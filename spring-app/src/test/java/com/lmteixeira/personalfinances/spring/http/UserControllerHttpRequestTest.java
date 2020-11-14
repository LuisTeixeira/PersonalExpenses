package com.lmteixeira.personalfinances.spring.http;

import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerHttpRequestTest {

    @Autowired
    private SpringUserController userController;

    @Test
    public void contextLoadsTest() {
        Assert.assertNotNull(userController);
    }

}
