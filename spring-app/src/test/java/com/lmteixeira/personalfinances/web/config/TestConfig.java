package com.lmteixeira.personalfinances.web.config;

import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.web.controller.UserController;
import com.lmteixeira.personalfinances.web.repository.TestUserRepository;
import com.lmteixeira.personalfinances.web.rest.SpringUserController;

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
