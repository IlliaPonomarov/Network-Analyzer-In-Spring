package com.network.analyzer.utility;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record ErrorResponse (String message, String details, HttpStatus status, Date timestamp) {
    public ErrorResponse {
        timestamp = new Date();
    }
}
