package com.lmteixeira.personalfinances.usecases.user;

import com.lmteixeira.personalfinances.usecases.config.TestConfig;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserUseCasesTest {

    private TestConfig config;
    private FindAllUsers findAllUsers;
    private CreateUser createUser;
    private FindUserByEmail findUserByEmail;

    @Before
    public void setup() {
        config = new TestConfig();
        findAllUsers = config.findAllUsers();
        createUser = config.createUser();
        findUserByEmail = config.findUserByEmail();
    }

    @Test
    public void findAllUsersShouldReturnAnEmptyListWhenNoUserWasAdded() {
        Assert.assertEquals(0, findAllUsers.findAllUsers().size());
    }

    @Test
    public void afterAddingOneUserFindAllUsersShouldReturnOneUser() {
        String userEmail = "test@gmail.com";
        createUser.create(userEmail);
        Assert.assertEquals(1, findAllUsers.findAllUsers().size());
    }

    @Test
    public void findUserByEmailShouldReturnThatUserWhenUserExists() {
        String userEmail = "test@gmail.com";
        createUser.create(userEmail);
        Assert.assertEquals(userEmail, findUserByEmail.findUserByEmail(userEmail).toString());
    }

    @Test
    public void findUserByEmailShouldThrowExceptionWhenUserDoesNotExist() {
        String userEmail = "test@gmail.com";
        boolean exceptionThrown = false;
        try {
            findUserByEmail.findUserByEmail(userEmail);
        } catch (UserNotFoundException ex) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

}
