package com.trusteeship.manage.service.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("AdminConfig")
@ConfigurationProperties(prefix = "super")
@Data
public class AdminConfig {
    private String admin;
}
