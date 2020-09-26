package com.lmteixeira.personalfinances.web.controller;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.web.models.UserUiModel;

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

    public UserUiModel createUser(UserUiModel userUiModel) {
        this.createUser.create(userUiModel.getEmail());
        return userUiModel;
    }

    public List<UserUiModel> getAllUsers() {
        List<User> allUsers = findAllUsers.findAllUsers();
        return allUsers.stream().map(UserUiModel::toUserUiModel).collect(Collectors.toList());
    }

    public UserUiModel getUserByEmail(String email) throws UserNotFoundException {
        User user = findUserByEmail.findUserByEmail(email);
        return UserUiModel.toUserUiModel(user);
    }
}
