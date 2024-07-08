package com.dux.application.exceptionhandler.exception;

import lombok.Data;

@Data
public class LoginException extends RuntimeException {

    private String message;
    private int code;

    public LoginException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}