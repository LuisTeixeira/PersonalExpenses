package com.lmteixeira.personalfinances.web.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserUiModel {

    @NotBlank
    @Email
    private String email;

    public UserUiModel(String email) {
        this.email = email;
    }
}
