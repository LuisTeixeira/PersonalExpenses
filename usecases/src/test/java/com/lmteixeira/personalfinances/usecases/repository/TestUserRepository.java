package com.lmteixeira.personalfinances.usecases.repository;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TestUserRepository implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users);
    }

}
