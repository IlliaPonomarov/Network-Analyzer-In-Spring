package com.network.analyzer.layers.network.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.network.analyzer.global.exceptions.ErrorResponse;

@ControllerAdvice
public class IPControllerAdvice {

    @ExceptionHandler(value = IPPacketsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse IPPacketsNotFoundExceptionHandler(IPPacketsNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ICMPPacketsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse ICMPPacketsNotFoundExceptionHandler(ICMPPacketsNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
