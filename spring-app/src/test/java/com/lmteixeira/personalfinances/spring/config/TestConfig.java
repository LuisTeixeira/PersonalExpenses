package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.spring.controller.UserController;
import com.lmteixeira.personalfinances.spring.repository.TestUserRepository;
import com.lmteixeira.personalfinances.spring.rest.SpringUserController;

public class TestConfig {

    public SpringUserController userController() {
        UserRepository userRepository = new TestUserRepository();
        CreateUser createUser = new CreateUser(userRepository);
        FindAllUsers findAllUsers = new FindAllUsers(userRepository);
        FindUserByEmail findUserByEmail = new FindUserByEmail(userRepository);
        UserController userController = new UserController(createUser, findAllUsers, findUserByEmail);
        return new SpringUserController(userController);
    }

}
