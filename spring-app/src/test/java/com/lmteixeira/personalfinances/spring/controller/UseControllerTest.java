package com.lmteixeira.personalfinances.spring.controller;

import com.lmteixeira.personalfinances.spring.config.TestConfig;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
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
        ResponseEntity<List<UserWeb>> responseEntity = userController.getAllUsers();
        Assert.assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    public void createUserShouldReturnStatus201() {
        UserWeb user = new UserWeb("test@test.com");
        ResponseEntity responseEntity = userController.createUser(user);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void createUserWithShouldReturnTheUserCreated() {
        UserWeb user = new UserWeb("test@test.com");
        ResponseEntity<UserWeb> responseEntity = userController.createUser(user);
        Assert.assertEquals(responseEntity.getBody().getEmail(), user.getEmail());
    }

    @Test
    public void afterCreatingUserGetAllUsersShouldReturnAListContainingTheAddedUser() {
        UserWeb user = new UserWeb("test@test.com");
        userController.createUser(user);
        ResponseEntity<List<UserWeb>> responseEntity = userController.getAllUsers();
        UserWeb returnedUser = responseEntity.getBody().get(0);
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void whenNoUserWasAddedGetUserByEmailShouldReturnStatus404() {
        ResponseEntity<UserWeb> responseEntity = userController.getUserByEmail("test@test.com");
        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void afterAddAUserGetUserByIdShouldReturnTheAddedUser() {
        UserWeb user = new UserWeb("test@test.com");
        userController.createUser(user);
        ResponseEntity<UserWeb> responseEntity = userController.getUserByEmail(user.getEmail());
        UserWeb returnedUser = responseEntity.getBody();
        Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
    }
}
