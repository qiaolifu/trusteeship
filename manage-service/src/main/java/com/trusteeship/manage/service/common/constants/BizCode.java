package com.trusteeship.manage.service.common.constants;

import com.core.exception.ApiException;
import com.core.exception.itf.ErrorCode;
import lombok.Getter;

@Getter
public enum BizCode implements ErrorCode {
	SUCCESS(0, "操作成功"),
	UNKNOWN_ERROR(-1, "未知异常"),
	IN_VALID_TOKEN(1000, "登录失效"),
	IN_VALID_USER(1001, "用户已失效"),
	USERNAME_ERROR(2000, "用户名格式不正确，只允许英文字母和数字"),
	PASSWORD_ERROR(2001, "密码格式不正确，只允许英文字母和数字"),
	DATABASE_IP_ERROR(2002, "数据库地址格式不正确"),
	DATABASE_KEY_ERROR(2003, "数据库密钥格式不正确"),
	PHONE_ERROR(2004, "手机号格式不正确"),
	USERNAME_EXIST(2005, "用户名已被注册"),
	IP_EXIST(2006, "IP地址已被注册"),
	DATABASE_EXIST(2007, "已绑定过数据库"),
	LOG_DATA_NOT_EXIST(2008, "找不到指定备份"),
	DATABASE_NOT_BINDING(2009, "未找到绑定数据库"),

	EXPIRE_USER(3001,"账户已过期"),
	ERROR_USER_PWD(3002,"用户名密码错误"),
	ONLY_VIP(4001,"此功能仅限VIP用户使用")
	//=============经销商操作返回码

	;
	private int code;
	private String msg;

	BizCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static void main(String[] args) {
		ApiException a = new ApiException(BizCode.SUCCESS);
		System.out.println(a.getCode());
		System.out.println(a.getMsg());
	}
}