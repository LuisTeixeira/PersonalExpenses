package com.lmteixeira.personalfinances.testdata;

import com.lmteixeira.personalfinances.domain.user.User;

public class UserTestData {

    private static UserTestData instance;

    private User[] users;

    private UserTestData() {
        this.users = new LoadUsersFromFile().getUsersFromFile();
    }

    public static UserTestData getInstance() {
        if (instance == null) {
            instance = new UserTestData();
        }
        return instance;
    }

    public User[] getUsers() {
        return this.users;
    }

}
