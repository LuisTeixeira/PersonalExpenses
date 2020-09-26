package com.lmteixeira.personalfinances.webadapter.controller;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;

import java.util.List;
import java.util.stream.Collectors;

public class UserController {

    private CreateUser createUser;
    private FindAllUsers findAllUsers;
    private FindUserByEmail findUserByEmail;

    public UserController(CreateUser createUser, FindAllUsers findAllUsers, FindUserByEmail findUserByEmail) {
        this.createUser = createUser;
        this.findAllUsers = findAllUsers;
        this.findUserByEmail = findUserByEmail;
    }

    public UserWeb createUser(UserWeb userUiModel) {
        this.createUser.create(userUiModel.getEmail());
        return userUiModel;
    }

    public List<UserWeb> getAllUsers() {
        List<User> allUsers = findAllUsers.findAllUsers();
        return allUsers.stream().map(UserWeb::toUserUiModel).collect(Collectors.toList());
    }

    public UserWeb getUserByEmail(String email) throws UserNotFoundException {
        User user = findUserByEmail.findUserByEmail(email);
        return UserWeb.toUserUiModel(user);
    }
}