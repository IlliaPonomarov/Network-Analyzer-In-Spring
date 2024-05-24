package com.network.analyzer.layers.physical.exceptions;

public class PacketsNotFoundException extends RuntimeException {
    public PacketsNotFoundException(String noEthernetPacketsFound) {
        super(noEthernetPacketsFound);
    }
}
