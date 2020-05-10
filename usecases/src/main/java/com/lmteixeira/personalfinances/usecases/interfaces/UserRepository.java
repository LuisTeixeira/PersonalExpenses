package com.lmteixeira.personalfinances.usecases.interfaces;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.List;

public interface UserRepository {
    void create(User user);

    List<User> all();

    User findUserByEmail(String email) throws EntityNotFoundException;
}
