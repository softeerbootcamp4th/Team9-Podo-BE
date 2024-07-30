package com.softeer.podo.event.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExistingCommentException extends RuntimeException {
    private String message;
}
