package com.dux.application.exceptionhandler.exception;

import lombok.Data;

@Data
public class CreateEntityException extends RuntimeException {

    private String message;
    private int code;

    public CreateEntityException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
