package com.dux.application.exceptionhandler;

import com.dux.application.exceptionhandler.exception.CreateEntityException;
import com.dux.application.exceptionhandler.exception.LoginException;
import com.dux.application.exceptionhandler.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(NotFoundException ex) {

        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateEntityException.class)
    public ResponseEntity<ExceptionResponse> handleCreateEntityExceptions(CreateEntityException ex) {

        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ExceptionResponse> handleLoginExceptions(LoginException ex) {

        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
