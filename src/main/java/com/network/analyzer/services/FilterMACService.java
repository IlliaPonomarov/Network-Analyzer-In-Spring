package com.network.analyzer.services;

public interface FilterMACService {

        String filterBySourceMAC(String source);
        String filterByDestinationMAC(String destination);
        String filterBySourceAndDestinationMACs(String source, String destination);

}
