package com.network.analyzer.services;

import org.pcap4j.packet.Packet;

import java.util.List;

public interface PacketService {

        boolean collectPackets();
        void setPackets(List<Packet> packets);
}
