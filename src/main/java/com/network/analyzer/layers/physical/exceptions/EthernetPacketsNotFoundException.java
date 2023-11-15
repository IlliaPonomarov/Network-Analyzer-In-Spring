package com.network.analyzer.layers.physical.exceptions;

public class EthernetPacketsNotFoundException extends RuntimeException {
    public EthernetPacketsNotFoundException(String noEthernetPacketsFound) {
        super(noEthernetPacketsFound);
    }
}
