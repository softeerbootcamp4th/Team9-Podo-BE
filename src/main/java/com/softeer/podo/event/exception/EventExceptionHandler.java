package com.softeer.podo.event.exception;

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
public class EventExceptionHandler {

    @ExceptionHandler(ExistingUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> existingPhoneNumbException(ExistingUserException e,  HttpServletRequest request) {
        log.warn("LOTSAPPLICATION-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.PHONENUM_EXIST_ERROR);
    }

    @ExceptionHandler(InvalidSelectionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> invalidSelectionException(InvalidSelectionException e,  HttpServletRequest request) {
        log.warn("LOTSAPPLICATION-002> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.INVALID_SELECTION_ERROR);
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> userNotExistException(UserNotExistException e,  HttpServletRequest request) {
        log.warn("LOTSAPPLICATION-003> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.USER_NOT_EXIST_ERROR);
    }

    @ExceptionHandler(ExistingCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> existingCommentException(ExistingCommentException e,  HttpServletRequest request) {
        log.warn("LOTSAPPLICATION-004> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new CommonResponse<>(ErrorCode.EXISTING_COMMENT_ERROR);
    }
}
