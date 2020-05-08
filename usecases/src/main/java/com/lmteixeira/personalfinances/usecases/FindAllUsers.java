package com.lmteixeira.personalfinances.usecases;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

import java.util.List;

public class FindAllUsers {

    UserRepository userRepository;

    public FindAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.all();
    }

}
