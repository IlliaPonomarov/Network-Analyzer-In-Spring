package com.network.analyzer.layers.physical.exceptions.advice;


import com.network.analyzer.global.exceptions.ErrorResponse;
import com.network.analyzer.layers.physical.exceptions.EthernetPacketsNotFoundException;
import com.network.analyzer.layers.physical.exceptions.IncorrectMacAddressException;
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

    @ExceptionHandler(EthernetPacketsNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEthernetPacketsNotFoundException(EthernetPacketsNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
