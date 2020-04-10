package com.core.exception;

import com.core.exception.itf.ErrorCode;
import lombok.Data;

/**
 * 类名称：BaseException  
 * 类描述：基础异常类
 */
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7773039158178752257L;


    public static int FAIL = -1;//默认失败码 用户可自行定义默认错误码

    private int code;//错误码
    
    private String msg;//错误消息

    private Exception ex;//错误异常源
    
    public BaseException() {
    }
    public BaseException(int code) {
        this(code, null, null);
    }

    public BaseException(String msg) {
        this(FAIL, msg, null);
    }

    public BaseException(int code, String msg) {
        this(code, msg, null);
    }

    public BaseException(int code, String msg, Exception ex) {
        this.code = code;
        this.msg = msg;
        this.ex = ex;
    }

    public BaseException(ErrorCode e) {
        this(e.getCode(), e.getMsg(), null);
    }
    
    public BaseException(Exception ex) {
        this(FAIL, null, ex);
    }

    public BaseException(ErrorCode e,Exception ex) {
        this(e.getCode(), e.getMsg(), ex);
    }

}