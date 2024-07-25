package com.softeer.podo.verification.exception;

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
public class VerificationExceptionHandler {

    @ExceptionHandler(MessageSendFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<?> messageSendFailException(MessageSendFailException e, HttpServletRequest request) {
        log.warn("VERIFICATION-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> tokenNotMatchException(TokenNotMatchException e, HttpServletRequest request) {
        log.warn("VERIFICATION-002> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.INVALID_TOKEN_ERROR);
    }
}
