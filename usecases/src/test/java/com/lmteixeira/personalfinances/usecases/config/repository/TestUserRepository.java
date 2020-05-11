package com.lmteixeira.personalfinances.usecases.config.repository;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
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

    @Override
    public User findUserByEmail(String email) throws EntityNotFoundException {
        for (User user : users) {
            if (user.toString().equals(email)) {
                return user;
            }
        }
        throw new EntityNotFoundException("User with email " + email + " not found");
    }

}
