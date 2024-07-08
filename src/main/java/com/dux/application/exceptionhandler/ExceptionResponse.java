package com.dux.application.exceptionhandler;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private String mensaje;
    private int codigo;

    public ExceptionResponse(String message, int code) {
        this.mensaje = message;
        this.codigo = code;
    }
}
