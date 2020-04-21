package com.trusteeship.manage.manage.api.controller;

import com.core.exception.ApiException;
import com.trusteeship.manage.service.base.RedisService;
import com.trusteeship.manage.service.common.constants.BizCode;
import com.trusteeship.manage.service.common.constants.KeyConstants;
import com.trusteeship.manage.service.common.constants.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public abstract class BaseController {

    @Autowired
    private RedisService redisService;

    public String checkToken(HttpServletRequest request) {
        String token = request.getHeader(KeyConstants.USER_TOKEN_KEY);
        if (null == token) throw new ApiException(BizCode.IN_VALID_TOKEN);
        Object name = redisService.get(new StringBuffer(RedisKey.USER_KEY_TOKEN_CUSTOMER).append(token).toString());
        if (null == name) throw new ApiException(BizCode.IN_VALID_TOKEN);
        return (String) name;
    }

}
