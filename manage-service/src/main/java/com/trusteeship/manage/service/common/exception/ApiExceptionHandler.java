package com.trusteeship.manage.service.common.exception;
import com.core.exception.ApiException;
import com.core.page.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 统一 json 异常处理
	 * @param ex JsonException
	 * @return 统一返回 json 格式
	 */
	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public R jsonErrorHandler(ApiException ex) {
		log.error("【ApiException】:{}", ex.getMsg());
		return R.fail(ex.getCode(),ex.getMsg());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public R exceptionHandler(Exception exception) {
		log.error("【Exception】:{}", exception.getMessage());
		return R.fail();
	}

}
