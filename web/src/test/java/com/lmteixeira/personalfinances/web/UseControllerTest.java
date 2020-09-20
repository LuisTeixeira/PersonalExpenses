package com.lmteixeira.personalfinances.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UseControllerTest {

    @Autowired
    MockMvc userController;

    @Test
    public void getAllUsersShouldReturnIsOk() throws Exception {
        ResultActions resultActions = userController.perform(get("/users"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getAllUsersShouldReturnEmptyListWhenNoUserWasCreated() throws Exception {
        ResultActions resultActions = userController.perform(get("/users"));
        resultActions.andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void createUserWithEmptyContentShouldReturnStatus() throws Exception {
        ResultActions resultActions = userController.perform(post("/users"));
        resultActions.andExpect(status().is(422));
    }

}
