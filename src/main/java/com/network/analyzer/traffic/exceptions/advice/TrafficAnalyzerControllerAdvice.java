package com.network.analyzer.traffic.exceptions.advice;

import com.network.analyzer.global.exceptions.ErrorResponse;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class TrafficAnalyzerControllerAdvice {

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMultipartFileException(MultipartException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePacketListIsEmptyException(PacketListIsEmptyException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
