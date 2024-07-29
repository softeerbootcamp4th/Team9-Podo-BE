package com.softeer.podo.event.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExistingUserException extends RuntimeException {
    private String message;
}
