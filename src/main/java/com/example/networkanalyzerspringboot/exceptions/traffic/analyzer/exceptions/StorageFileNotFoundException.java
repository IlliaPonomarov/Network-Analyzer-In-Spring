package com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions;

public class StorageFileNotFoundException extends StorageException{

        public StorageFileNotFoundException(String message) {
            super(message);
        }

        public StorageFileNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
}
