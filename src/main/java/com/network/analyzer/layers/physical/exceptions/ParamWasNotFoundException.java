package com.network.analyzer.layers.physical.exceptions;

public class ParamWasNotFoundException extends RuntimeException {
    public ParamWasNotFoundException(String format) {
        super(format);
    }
}
