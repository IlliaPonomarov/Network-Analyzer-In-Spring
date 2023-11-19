package com.network.analyzer.layers.network.exceptions;

public class HardwareTypeIsEmptyException extends RuntimeException {
    public HardwareTypeIsEmptyException(String opcodeIsEmpty) {
        super(opcodeIsEmpty);
    }
}
