package com.network.analyzer.layers.physical.exceptions;

public class IncorrectMacAddressException extends RuntimeException {
    public IncorrectMacAddressException(String incorrectMacAddressFormat) {
        super(incorrectMacAddressFormat);
    }
}
