package com.network.analyzer.storage.exceptions.advices;

import com.network.analyzer.storage.exceptions.MinioDBServiceException;
import com.network.analyzer.utility.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class MinioDBControllerAdvice {

    @ExceptionHandler(MinioDBServiceException.class)
    public ErrorResponse handleMinioDBServiceException(MinioDBServiceException e) {
        return new ErrorResponse(e.getMessage(), e.getStackTrace().toString(), HttpStatus.BAD_REQUEST, new Date());
    }
}
