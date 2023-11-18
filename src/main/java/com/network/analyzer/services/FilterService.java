package com.network.analyzer.services;

import com.network.analyzer.layers.network.models.InternetProtocol;
import org.pcap4j.packet.Packet;

import java.util.List;

public interface FilterService {

    boolean collectPackets();
    List<?> filterBySource(String source, String version);
    List<?> filterByDestination(String destination, String version);
    List<?> filterBySourceAndDestination(String source, String destination, String version);
    void setPackets(List<Packet> packets);
}
