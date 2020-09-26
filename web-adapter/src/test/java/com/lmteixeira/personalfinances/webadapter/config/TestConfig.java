package com.lmteixeira.personalfinances.webadapter.config;

import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import com.lmteixeira.personalfinances.webadapter.repository.TestUserRepository;

public class TestConfig {

    public UserController userController() {
        UserRepository userRepository = new TestUserRepository();
        CreateUser createUser = new CreateUser(userRepository);
        FindAllUsers findAllUsers = new FindAllUsers(userRepository);
        FindUserByEmail findUserByEmail = new FindUserByEmail(userRepository);
        return new UserController(createUser, findAllUsers, findUserByEmail);
    }

}
