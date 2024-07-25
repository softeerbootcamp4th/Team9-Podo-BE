package com.softeer.podo.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * API 공통 응답을 위한 응답 클래스
 * @param <T>
 */
@Getter
@Setter
public class CommonResponse<T> {
    private Boolean isSuccess;
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 성공
    public CommonResponse(T result) {
        this.isSuccess = ErrorCode.SUCCESS.getIsSuccess();
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.result = result;
    }

    // 오류 발생
    public CommonResponse(ErrorCode errorCode) {
        this.isSuccess = errorCode.getIsSuccess();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    // 오류 발생
    public CommonResponse(ErrorCode errorCode, T result) {
        this.isSuccess = errorCode.getIsSuccess();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result = result;
    }
}
