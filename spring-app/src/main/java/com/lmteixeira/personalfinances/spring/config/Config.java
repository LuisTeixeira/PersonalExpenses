package com.lmteixeira.personalfinances.spring.config;

import com.lmteixeira.personalfinances.hazelcastrepo.HazelcastRepository;
import com.lmteixeira.personalfinances.webadapter.config.SpringConfig;
import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final HazelcastRepository hazelcastRepository = new HazelcastRepository();
    private final SpringConfig springConfig = new SpringConfig(hazelcastRepository);

    @Bean
    public UserController userController() {
        return this.springConfig.userController();
    }
}
