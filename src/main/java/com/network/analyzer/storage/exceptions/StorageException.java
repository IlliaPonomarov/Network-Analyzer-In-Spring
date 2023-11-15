package com.network.analyzer.storage.exceptions;

import java.io.IOException;

public class StorageException extends Throwable {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
