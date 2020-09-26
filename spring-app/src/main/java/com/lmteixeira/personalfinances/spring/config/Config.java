package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.hazelcastrepo.HazelcastRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.user.CreateUser;
import com.lmteixeira.personalfinances.usecases.user.FindAllUsers;
import com.lmteixeira.personalfinances.usecases.user.FindUserByEmail;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final UserRepository userRepository = new HazelcastRepository();

    @Bean
    public CreateUser createUser() {
        return new CreateUser(userRepository);
    }

    @Bean
    public FindAllUsers findAllUsers() {
        return new FindAllUsers(userRepository);
    }

    @Bean
    public FindUserByEmail findUserByEmail() {
        return new FindUserByEmail(userRepository);
    }

    public UserController userController() {
        return new UserController(createUser(), findAllUsers(), findUserByEmail());
    }
}
