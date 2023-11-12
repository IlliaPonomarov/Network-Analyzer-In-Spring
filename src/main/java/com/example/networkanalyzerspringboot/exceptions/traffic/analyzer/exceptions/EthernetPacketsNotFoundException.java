package com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions;

public class EthernetPacketsNotFoundException extends RuntimeException {
    public EthernetPacketsNotFoundException(String noEthernetPacketsFound) {
        super(noEthernetPacketsFound);
    }
}
