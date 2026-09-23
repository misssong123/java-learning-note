package org.example.springbootdemo.stardemo.configuration;

import org.example.springbootdemo.stardemo.dto.User;
import org.springframework.context.annotation.Bean;

public class UserConfig {
    @Bean
    public User user() {
        System.out.println("user bean init");
        return new User("张三",12);
    }
}
