package com.lmteixeira.personalfinances.usecases.config;

import com.lmteixeira.personalfinances.usecases.CreateUser;
import com.lmteixeira.personalfinances.usecases.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.repository.TestUserRepository;

public class TestConfig {

    private UserRepository userRepository = new TestUserRepository();

    public CreateUser createUser() {
        return new CreateUser(userRepository);
    }

    public FindAllUsers findAllUsers() {
        return new FindAllUsers(userRepository);
    }
}
