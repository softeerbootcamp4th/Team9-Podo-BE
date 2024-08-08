package com.softeer.podo.event.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LotsShareLinkNotExistsException extends RuntimeException {
    private String message;
}
