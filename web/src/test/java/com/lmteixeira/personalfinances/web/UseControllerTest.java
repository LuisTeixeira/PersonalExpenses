package com.lmteixeira.personalfinances.web;

import com.lmteixeira.personalfinances.web.models.UserUiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UseControllerTest {

    private UserController userController;

    @Before
    public void setup() {
        this.userController = new UserController();
    }

    @Test
    public void getAllUsersShouldReturnIsOk() throws Exception {
        ResponseEntity responseEntity = userController.getAllUsers();
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllUsersShouldReturnEmptyListWhenNoUserWasCreated() throws Exception {
        ResponseEntity<List<Object>> responseEntity = userController.getAllUsers();
        Assert.assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    public void createUserWithValidJsonShouldReturnStatus201() throws Exception {
        UserUiModel user = new UserUiModel("test@test.com");
        ResponseEntity responseEntity = userController.createUser(user);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
