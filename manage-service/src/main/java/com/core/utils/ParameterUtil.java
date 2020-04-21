package com.core.utils;

import com.core.exception.ApiException;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.common.constants.BizCode;

import java.util.regex.Pattern;

public class ParameterUtil {





    public static void checkParameter(TUser tUser) {

        String phoneRegex = "^1(3|4|5|7|8)\\d{9}$";

        if (null == tUser.getPhone() || "".equals(tUser.getPhone()) || !tUser.getPhone().matches(phoneRegex)) {
            throw new ApiException(BizCode.PHONE_ERROR);
        }
        checkParameterUnAndPsw(tUser);

    }
    public static void checkParameter(TDatabase database) {
        String regex = "^[^0-9][\\w_]{5,9}$";
        String urlRegex = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

        if (null == database.getUrl() || "".equals(database.getUrl()) || !database.getUrl().matches(urlRegex)) {
            throw new ApiException(BizCode.DATABASE_IP_ERROR);
        }
        if (null == database.getPassword() || "".equals(database.getPassword()) || !database.getPassword().matches(regex)) {
            throw new ApiException(BizCode.DATABASE_KEY_ERROR);
        }
        if (null == database.getUser() || "".equals(database.getUser()) || !isValid(database.getUser())) {
            throw new ApiException(BizCode.USERNAME_ERROR);
        }

    }


    public static void checkParameterUnAndPsw(TUser tUser) {
        if (null == tUser.getUser() || "".equals(tUser.getUser()) || !isValid(tUser.getUser())) {
            throw new ApiException(BizCode.USERNAME_ERROR);
        }
        if (null == tUser.getPassword() || "".equals(tUser.getPassword()) || !isValid(tUser.getPassword())) {
            throw new ApiException(BizCode.PASSWORD_ERROR);
        }
    }

    /**
     * 防注入
     */
    private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
    //        b  表示 限定单词边界  比如  select 不通过   1select则是可以的
    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

    private static boolean isValid(String str) {
        if (sqlPattern.matcher(str).find()) {
            return false;
        }
        return true;
    }
}
