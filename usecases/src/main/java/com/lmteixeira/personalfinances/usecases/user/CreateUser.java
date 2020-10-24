package com.lmteixeira.personalfinances.usecases.user;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.budget.CreateBudget;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

public class CreateUser {

    private UserRepository userRepository;
    private CreateBudget createBudget;

    public CreateUser(UserRepository userRepository, CreateBudget createBudget) {
        this.userRepository = userRepository;
        this.createBudget = createBudget;
    }

    public void create(String userEmail) throws UserNotFoundException {
        User user = new User(userEmail);
        userRepository.create(user);
        createBudget.createBudget(userEmail);
    }

}
