package com.lmteixeira.personalfinances.spring.controller;

import com.lmteixeira.personalfinances.spring.config.TestConfig;
import com.lmteixeira.personalfinances.spring.rest.SpringIncomeController;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;
import com.lmteixeira.personalfinances.webadapter.model.TransactionWeb;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class IncomeControllerTest {

    private static final String USER_EMAIL = "user@test.com";

    private TestConfig config;
    private SpringUserController userController;
    private SpringIncomeController incomeController;

    @Before
    public void setup() {
        this.config = new TestConfig();
        this.userController = config.userController();
        this.incomeController = config.incomeController();
        this.userController.createUser(new UserWeb(USER_EMAIL));
    }

    @Test
    public void getAllIncomeShouldReturnIsOk() {
        ResponseEntity responseEntity = incomeController.getAllIncome(USER_EMAIL);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllIncomeShouldReturnEmptyListWhenNoIncomeWereAdded() {
        ResponseEntity<List> responseEntity = incomeController.getAllIncome(USER_EMAIL);
        Assert.assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void getAllIncomeShouldReturnAListWithTheSameNumberOfElementsAsIncomeAdded() {
        TransactionWeb incomeWeb = new TransactionWeb("Test Income", 23d);
        incomeController.add(USER_EMAIL, incomeWeb);
        ResponseEntity<List<TransactionWeb>> responseEntity = incomeController.getAllIncome(USER_EMAIL);
        Assert.assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void addIncomeShouldReturnCode201() {
        TransactionWeb incomeWeb = new TransactionWeb("Test Income", 232d);
        ResponseEntity responseEntity = incomeController.add(USER_EMAIL, incomeWeb);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
