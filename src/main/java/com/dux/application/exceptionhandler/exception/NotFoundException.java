package com.dux.application.exceptionhandler.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private String message;
    private int code;

    public NotFoundException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
