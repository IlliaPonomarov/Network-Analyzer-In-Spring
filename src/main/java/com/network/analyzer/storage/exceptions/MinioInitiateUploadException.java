package com.network.analyzer.storage.exceptions;

public class MinioInitiateUploadException extends RuntimeException {
    public MinioInitiateUploadException(String failedToInitiateUpload, Exception e) {
        super(failedToInitiateUpload, e);
    }
}
