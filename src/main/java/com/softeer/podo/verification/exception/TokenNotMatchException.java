package com.softeer.podo.verification.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenNotMatchException extends RuntimeException{
    private String message;
}
