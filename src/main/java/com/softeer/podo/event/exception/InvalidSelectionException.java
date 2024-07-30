package com.softeer.podo.event.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidSelectionException extends RuntimeException {
    private String message;
}
