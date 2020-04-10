package com.core.exception;

import com.core.exception.itf.ErrorCode;
import lombok.Data;

@Data
public class ApiException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ApiException() {

	}
	public ApiException(ErrorCode e) {
		super(e.getCode(), e.getMsg(), null);
	}
	public ApiException(ErrorCode e,Exception ex) {
		super(e.getCode(), e.getMsg(), ex);
	}
}
