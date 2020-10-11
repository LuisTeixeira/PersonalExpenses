package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.hazelcastrepo.HazelcastUserRepository;
import com.lmteixeira.personalfinances.webadapter.config.SpringConfig;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final HazelcastUserRepository hazelcastUserRepository = new HazelcastUserRepository();
    private final SpringConfig springConfig = new SpringConfig(hazelcastUserRepository);

    @Bean
    public UserController userController() {
        return this.springConfig.userController();
    }
}
