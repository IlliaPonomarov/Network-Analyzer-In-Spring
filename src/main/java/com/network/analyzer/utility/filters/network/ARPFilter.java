package com.network.analyzer.utility.filters.network;

import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;
import java.util.function.Predicate;

public interface ARPFilter {

    static Predicate<? super Packet> getARP() {
        return packet -> packet.get(ArpPacket.class) != null;
    }
}
