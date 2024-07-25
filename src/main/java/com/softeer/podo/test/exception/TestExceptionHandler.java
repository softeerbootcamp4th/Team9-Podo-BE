package com.softeer.podo.test.exception;

import com.nimbusds.jose.JOSEException;
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
public class TestExceptionHandler {

    @ExceptionHandler(JOSEException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> joseException(JOSEException e, HttpServletRequest request) {
        log.warn("TEST-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
