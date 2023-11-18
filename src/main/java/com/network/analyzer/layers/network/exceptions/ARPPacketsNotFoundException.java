package com.network.analyzer.layers.network.exceptions;

public class ARPPacketsNotFoundException extends RuntimeException {
    public ARPPacketsNotFoundException(String arpPacketsNotFound) {
        super(arpPacketsNotFound);
    }
}
