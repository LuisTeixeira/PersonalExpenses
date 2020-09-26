package com.lmteixeira.personalfinances.webadapter.model;


import com.lmteixeira.personalfinances.domain.user.User;

public class UserWeb {

    private String email;

    public UserWeb(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public static UserWeb toUserUiModel(User user) {
        return new UserWeb(user.getEmail());
    }
}
