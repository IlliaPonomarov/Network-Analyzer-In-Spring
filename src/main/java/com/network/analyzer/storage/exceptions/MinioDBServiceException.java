package com.network.analyzer.storage.exceptions;

public class MinioDBServiceException extends RuntimeException {
    public MinioDBServiceException(String format) {
        super(format);
    }
}
