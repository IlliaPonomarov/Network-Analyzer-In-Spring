package com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions;

public class IncorrectMacAddressException extends RuntimeException {
    public IncorrectMacAddressException(String incorrectMacAddressFormat) {
        super(incorrectMacAddressFormat);
    }
}
