package com.softeer.podo.admin.model.exception;

import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.common.response.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdminExceptionHandler {

	@ExceptionHandler(EventNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResponse<?> eventNotFoundException(EventNotFoundException e, HttpServletRequest request) {
		log.warn("ADMIN-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
		return new CommonResponse<>(ErrorCode.EVENT_NOT_FOUND_ERROR);
	}

}
