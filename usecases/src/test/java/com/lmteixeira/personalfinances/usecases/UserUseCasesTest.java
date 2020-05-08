package com.lmteixeira.personalfinances.usecases;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserUseCasesTest {

    private TestConfig config;

    @Before
    public void setup() {
        config = new TestConfig();
    }

    @Test
    public void findAllUsersShouldReturnAnEmptyListWhenNoUserWasAdded() {
        FindAllUsers findAllUsers = config.findAllUsers();
        Assert.assertEquals(0, findAllUsers.findAllUsers().size());
    }

    @Test
    public void afterAddingOneUserTheUserFindAllShouldReturnOneUser() {
        String userEmail = "test@gmail.com";
        CreateUser createUser = config.createUser();
        FindAllUsers findAllUsers = config.findAllUsers();
        createUser.create(userEmail);
        Assert.assertEquals(1, findAllUsers.findAllUsers().size());
    }

}
