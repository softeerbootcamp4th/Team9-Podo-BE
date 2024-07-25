package com.softeer.podo.verification.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageSendFailException extends RuntimeException{
    private String message;
}
