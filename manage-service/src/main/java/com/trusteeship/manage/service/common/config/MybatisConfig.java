package com.trusteeship.manage.service.common.config;

import com.trusteeship.manage.service.common.interceptor.MybatisInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.trusteeship.**.service.dao")
public class MybatisConfig {
    @Bean
    public MybatisInterceptor customInterceptor() {
        return new MybatisInterceptor();
    }
}
