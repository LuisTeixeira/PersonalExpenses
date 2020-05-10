package com.lmteixeira.personalfinances.usecases.user;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.EntityNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

public class FindUserByEmail {

    private UserRepository userRepository;

    public FindUserByEmail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String userEmail) {
        try {
            return userRepository.findUserByEmail(userEmail);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User with email " + userEmail + " not found");
        }
    }
}