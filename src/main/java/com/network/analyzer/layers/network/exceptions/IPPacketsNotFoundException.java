package com.network.analyzer.layers.network.exceptions;

public class IPPacketsNotFoundException extends RuntimeException {

    public IPPacketsNotFoundException(String ipPacketsNotFound) {
        super(ipPacketsNotFound);
    }
}
