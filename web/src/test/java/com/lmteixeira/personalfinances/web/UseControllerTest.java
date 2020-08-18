package com.lmteixeira.personalfinances.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UseControllerTest {

    @Autowired
    MockMvc userController;

    @Test
    public void getAllUsersTest() throws Exception {
        ResultActions resultActions = userController.perform(get("/users"));
        resultActions.andExpect(status().isOk());
    }

}
