package com.core.exception.itf;

/**
 * 异常信息基础接口
 */
public interface ErrorCode {
	
	/**
	 * 错误码
	 * @return
	 */
	int getCode();
	
	/**
	 * 错误消息
	 * @return
	 */
	String getMsg();
}
