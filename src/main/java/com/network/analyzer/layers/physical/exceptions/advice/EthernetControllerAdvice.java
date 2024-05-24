package com.network.analyzer.layers.physical.exceptions.advice;


import com.network.analyzer.global.exceptions.ErrorResponse;
import com.network.analyzer.layers.physical.exceptions.PacketsNotFoundException;
import com.network.analyzer.layers.physical.exceptions.IncorrectMacAddressException;
import com.network.analyzer.layers.physical.exceptions.ParamWasNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EthernetControllerAdvice {



    @ExceptionHandler(IncorrectMacAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectMacAddressException(IncorrectMacAddressException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PacketsNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEthernetPacketsNotFoundException(PacketsNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParamWasNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleParamWasNotFoundException(ParamWasNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
