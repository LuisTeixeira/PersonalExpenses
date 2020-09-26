package com.lmteixeira.personalfinances.spring;

import com.lmteixeira.personalfinances.spring.config.TestConfig;
import com.lmteixeira.personalfinances.spring.models.UserUiModel;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
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

    @Test
    public void whenNoUserWasAddedGetUserByEmailShouldReturnStatus404() {
        ResponseEntity<UserUiModel> responseEntity = userController.getUserByEmail("test@test.com");
        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void afterAddAUserGetUserByIdShouldReturnTheAddedUser() {
        UserUiModel user = new UserUiModel("test@test.com");
        userController.createUser(user);
        ResponseEntity<UserUiModel> responseEntity = userController.getUserByEmail(user.getEmail());
        UserUiModel returnedUser = responseEntity.getBody();
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }
}
