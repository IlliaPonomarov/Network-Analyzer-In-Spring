package com.network.analyzer.utility.filters.network;

import org.pcap4j.packet.IcmpV4CommonPacket;
import org.pcap4j.packet.IcmpV6CommonPacket;
import org.pcap4j.packet.Packet;

import java.util.function.Predicate;

public interface ICMPFilter {

    static Predicate<? super Packet> getIcmpV4() {
        return packet -> packet.get(IcmpV4CommonPacket.class) != null;
    }

    static Predicate<? super Packet> getIcmpV6() {
        return packet -> packet.get(IcmpV6CommonPacket.class) != null;
    }
}
