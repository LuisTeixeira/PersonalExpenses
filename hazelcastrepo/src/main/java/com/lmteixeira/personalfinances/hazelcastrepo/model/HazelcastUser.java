package com.lmteixeira.personalfinances.hazelcastrepo.model;

import com.lmteixeira.personalfinances.domain.user.User;

import java.io.Serializable;

public class HazelcastUser implements Serializable {

    private static final long serialVerisionUID = 1L;

    private String email;

    private HazelcastUser() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public User toUser() {
        User user = new User(this.email);
        return user;
    }

    public static HazelcastUser fromUser(User user) {
        HazelcastUser hazelcastUser = new HazelcastUser();
        hazelcastUser.setEmail(user.getEmail());
        return hazelcastUser;
    }
}
