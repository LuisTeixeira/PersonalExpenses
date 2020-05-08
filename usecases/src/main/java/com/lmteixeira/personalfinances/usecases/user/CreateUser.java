package com.lmteixeira.personalfinances.usecases.user;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

public class CreateUser {

    private UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(String userEmail) {
        User user = new User(userEmail);
        userRepository.create(user);
    }

}
