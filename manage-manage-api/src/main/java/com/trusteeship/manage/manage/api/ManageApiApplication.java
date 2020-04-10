package com.trusteeship.manage.manage.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.trusteeship.manage")
@MapperScan(value = {"com.trusteeship.manage"})
public class ManageApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApiApplication.class, args);
    }
}
