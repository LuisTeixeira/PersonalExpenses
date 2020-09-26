package com.lmteixeira.personalfinances.web;

import com.lmteixeira.personalfinances.web.config.TestConfig;
import com.lmteixeira.personalfinances.web.models.UserUiModel;
import com.lmteixeira.personalfinances.web.rest.SpringUserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UseControllerTest {

    private TestConfig config;
    private SpringUserController userController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.userController = config.userController();
    }

    @Test
    public void getAllUsersShouldReturnIsOk() {
        ResponseEntity responseEntity = userController.getAllUsers();
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllUsersShouldReturnEmptyListWhenNoUserWasCreated() {
        ResponseEntity<List<UserUiModel>> responseEntity = userController.getAllUsers();
        Assert.assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    public void createUserShouldReturnStatus201() {
        UserUiModel user = new UserUiModel("test@test.com");
        ResponseEntity responseEntity = userController.createUser(user);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void createUserWithShouldReturnTheUserCreated() {
        UserUiModel user = new UserUiModel("test@test.com");
        ResponseEntity<UserUiModel> responseEntity = userController.createUser(user);
        Assert.assertEquals(responseEntity.getBody().getEmail(), user.getEmail());
    }

    @Test
    public void afterCreatingUserGetAllUsersShouldReturnAListContainingTheAddedUser() {
        UserUiModel user = new UserUiModel("test@test.com");
        userController.createUser(user);
        ResponseEntity<List<UserUiModel>> responseEntity = userController.getAllUsers();
        UserUiModel returnedUser = responseEntity.getBody().get(0);
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }

}
