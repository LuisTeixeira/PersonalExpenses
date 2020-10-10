package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.webadapter.config.TestConfig;
import com.lmteixeira.personalfinances.webadapter.exception.UserNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserControllerTest {

    private TestConfig config;
    private UserController userController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        userController = this.config.userController();
    }

    @Test
    public void getAllUserShouldReturnEmptyListWhenNoUserIsAdded() {
        List<UserWeb> users = userController.getAllUsers();
        Assert.assertEquals(0, users.size());
    }

    @Test
    public void getAllUserShouldReturnContainingUserAdded() {
        UserWeb user = new UserWeb("test@test.com");
        userController.createUser(user);
        UserWeb returnedUser = userController.getAllUsers().get(0);
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void getUserByEmailShouldThrowUserWebNotFoundExceptionWhenUserDoesNotExist() {
        boolean exceptionThrown = false;
        try {
            UserWeb user = userController.getUserByEmail("test@test.com");
        } catch (UserNotFoundWebException exception) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getUserByEmailShouldReturnUserAdded() throws UserNotFoundWebException {
        UserWeb user = new UserWeb("test@test.com");
        userController.createUser(user);
        UserWeb returnedUser = userController.getUserByEmail("test@test.com");
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }
}
