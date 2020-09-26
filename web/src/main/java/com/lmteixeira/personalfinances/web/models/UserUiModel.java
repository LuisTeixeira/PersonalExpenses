package com.lmteixeira.personalfinances.web.models;

import com.lmteixeira.personalfinances.domain.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserUiModel {

    @NotBlank
    @Email
    private String email;

    public UserUiModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public static UserUiModel toUserUiModel(User user) {
        return new UserUiModel(user.getEmail());
    }
}
