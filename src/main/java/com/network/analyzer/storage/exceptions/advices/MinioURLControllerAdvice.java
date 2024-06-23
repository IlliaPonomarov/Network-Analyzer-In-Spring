package com.network.analyzer.storage.exceptions.advices;

import com.network.analyzer.storage.exceptions.MinioInitiateUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MinioURLControllerAdvice {

    @ExceptionHandler(MinioInitiateUploadException.class)
    public String handleMinioInitiateUploadException(MinioInitiateUploadException e) {
        return e.getMessage();
    }
}
