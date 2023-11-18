package com.network.analyzer.layers.network.exceptions;

public class ICMPPacketsNotFoundException extends RuntimeException {
    public ICMPPacketsNotFoundException(String icmpPacketsNotFound) {
        super(icmpPacketsNotFound);
    }
}
