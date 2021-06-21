package com.tony.demo;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.tony.demo.bean.User;

@SpringBootApplication
@EnableCaching
public class MainApplication {
    @Bean
    public User userBean() {
        User user = new User();
        user.setName("zhangsan");
        return user;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        User user = context.getBean(User.class);
    }
}
