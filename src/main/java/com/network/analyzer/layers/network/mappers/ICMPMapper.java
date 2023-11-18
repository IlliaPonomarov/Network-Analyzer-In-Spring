package com.network.analyzer.layers.network.mappers;

import com.network.analyzer.layers.network.models.ICMP;
import org.pcap4j.packet.IcmpV4CommonPacket;
import org.pcap4j.packet.IcmpV6CommonPacket;
import org.pcap4j.packet.Packet;

public class ICMPMapper {

    public static ICMP toICMPv4(Packet packet) {
        IcmpV4CommonPacket icmpV4CommonPacket = packet.get(IcmpV4CommonPacket.class);
        String type = icmpV4CommonPacket.getHeader().getType().name();
        String code = icmpV4CommonPacket.getHeader().getCode().name();
        int checksum = icmpV4CommonPacket.getHeader().getChecksum();
        byte[] payload = icmpV4CommonPacket.getPayload().getRawData();

        return new ICMP(type, code, checksum, payload);
    }

    public static ICMP toICMPv6(Packet packet) {
        IcmpV6CommonPacket icmpV4CommonPacket = packet.get(IcmpV6CommonPacket.class);
        String type = icmpV4CommonPacket.getHeader().getType().name();
        String code = icmpV4CommonPacket.getHeader().getCode().name();
        int checksum = icmpV4CommonPacket.getHeader().getChecksum();
        byte[] payload = icmpV4CommonPacket.getPayload().getRawData();

        return new ICMP(type, code, checksum, payload);
    }
}
