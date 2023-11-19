package com.network.analyzer.services;

import com.network.analyzer.layers.network.models.ARP;
import org.pcap4j.packet.Packet;

import java.util.List;

public interface FilterMACService {

        List<?> filterBySourceAndDestinationMACs(String source, String destination);

        List<?> filterBySourceMacAddress(String lowerCase);
        List<?> filterByDestinationMacAddress(String lowerCase);

        void setPackets(List<Packet> packets);
}
