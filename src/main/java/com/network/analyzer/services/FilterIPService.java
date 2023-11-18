package com.network.analyzer.services;

import org.pcap4j.packet.Packet;

import java.util.List;

public interface FilterIPService {

    boolean collectPackets();
    List<?> filterBySource(String source, String version);
    List<?> filterByDestination(String destination, String version);
    List<?> filterBySourceAndDestination(String source, String destination, String version);
    void setPackets(List<Packet> packets);
}
