package com.network.analyzer.layers.network.exceptions;

public class OpcodeIsEmptyException extends RuntimeException {
    public OpcodeIsEmptyException(String opcodeIsEmpty) {
        super(opcodeIsEmpty);
    }
}
