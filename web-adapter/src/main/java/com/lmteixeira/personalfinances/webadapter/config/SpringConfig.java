package com.lmteixeira.personalfinances.webadapter.config;

import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;

public class SpringConfig {

    private UserRepository userRepository;

    public SpringConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserController userController() {
        return new UserController(createUser(), findAllUsers(), findUserByEmail());
    }

    private FindUserByEmail findUserByEmail() {
        return new FindUserByEmail(userRepository);
    }

    private FindAllUsers findAllUsers() {
        return new FindAllUsers(userRepository);
    }

    private CreateUser createUser() {
        return new CreateUser(userRepository);
    }


}