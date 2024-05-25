package com.network.analyzer.utility.filters.network;

import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

import java.util.function.Predicate;

public interface IPFilter {

    static Predicate<? super Packet>  getIPv4() {
        return packet -> packet.get(IpV4Packet.class) != null;
    }

}
