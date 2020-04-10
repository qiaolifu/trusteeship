package com.core.utils;

import com.core.exception.ApiException;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.common.constants.BizCode;

import java.util.List;

public class ParameterUtil {


    public static void checkParameter(TUser tUser) {
        String regex = "^[a-z0-9A-Z]+$";
        String phoneRegex = "^1(3|4|5|7|8)\\d{9}$";

        if (null == tUser.getPhone() || "".equals(tUser.getPhone()) || !tUser.getPhone().matches(phoneRegex)) {
            throw new ApiException(BizCode.PHONE_ERROR);
        }
        checkParameterUnAndPsw(tUser, regex);

    }
    public static void checkParameter(TDatabase database) {
        String regex = "^[a-z0-9A-Z]+$";
        String urlRegex = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        String portRegex = "^[1-9]$|(^[1-9][0-9]$)|(^[1-9][0-9][0-9]$)|(^[1-9][0-9][0-9][0-9]$)|(^[1-6][0-5][0-5][0-3][0-5]$)";


        String[] dataUrl = database.getUrl().split(":");
        if (dataUrl.length != 2) {
            throw new ApiException(BizCode.DATABASE_IP_ERROR);
        }
        if (null == dataUrl[0] || "".equals(dataUrl[0]) || !dataUrl[0].matches(urlRegex)) {
            throw new ApiException(BizCode.DATABASE_IP_ERROR);
        }
        if (null == dataUrl[1] || "".equals(dataUrl[1]) || !dataUrl[1].matches(portRegex)) {
            throw new ApiException(BizCode.DATABASE_IP_ERROR);
        }
        if (null == database.getPassword() || "".equals(database.getPassword()) || !database.getPassword().matches(regex)) {
            throw new ApiException(BizCode.DATABASE_KEY_ERROR);
        }
        if (null == database.getUser() || "".equals(database.getUser()) || !database.getUser().matches(regex)) {
            throw new ApiException(BizCode.USERNAME_ERROR);
        }

    }


    public static void checkParameterUnAndPsw(TUser tUser, String regex) {
        if (null == tUser.getUser() || "".equals(tUser.getUser()) || !tUser.getUser().matches(regex)) {
            throw new ApiException(BizCode.USERNAME_ERROR);
        }
        if (null == tUser.getPassword() || "".equals(tUser.getPassword()) || !tUser.getPassword().matches(regex)) {
            throw new ApiException(BizCode.PASSWORD_ERROR);
        }
    }
}
