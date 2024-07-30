package com.softeer.podo.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //success
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공했습니다."),

    //error
    INTERNAL_SERVER_ERROR(false,HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부에서 문제가 발생했습니다."),
    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "해당 로그인 정보는 존재하지 않습니다."),
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED.value(), "권한이 없습니다."),
    S3_REGISTER_IMAGE_FAILURE_ERROR(false, HttpStatus.BAD_REQUEST.value(), "s3 이미지 저장 중 문제가 발생했습니다."),

    //auth
    EMAIL_EXISTS_ERROR(false, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    INVALID_EMAIL_ERROR(false, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일 정보입니다."),
    INVALID_PASSWORD_ERROR(false, HttpStatus.BAD_REQUEST.value(), "비밀번호를 확인해주세요. 카카오 계정이라면 카카오 로그인으로 시도해주세요."),
    INVALID_VERIFICATION_TOKEN_ERROR(false, HttpStatus.BAD_REQUEST.value(), "인증 토큰 정보를 찾을 수 없습니다. (사용자 정보가 잘못되었거나 인증 시간이 초과되었습니다)"),
    INVALID_ACCESS_TOKEN_ERROR(false, HttpStatus.BAD_REQUEST.value(), "AccessToken 정보를 찾을 수 없습니다."),
    INVALID_REFRESH_TOKEN_ERROR(false, HttpStatus.BAD_REQUEST.value(), "RefreshToken 정보를 찾을 수 없습니다."),
    USERNAME_EXISTS_ERROR(false, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),

    //event_lots
    PHONENUM_EXIST_ERROR(false, HttpStatus.BAD_REQUEST.value(),"이미 존재하는 전화번호입니다."),
    INVALID_SELECTION_ERROR(false, HttpStatus.BAD_REQUEST.value(), "선택지 형식이 잘못되었습니다."),
    USER_NOT_EXIST_ERROR(false, HttpStatus.BAD_REQUEST.value(), "해당 사용자가 아직 이벤트에 응모하지 않았습니다."),
    EXISTING_COMMENT_ERROR(false, HttpStatus.BAD_REQUEST.value(),"이미 기대평을 작성했습니다."),
    ;



    private Boolean isSuccess;
    private int code;
    private String message;

    ErrorCode(Boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
