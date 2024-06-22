package com.network.analyzer.ddos;

import com.network.analyzer.ddos.dtos.DdosIPv4DTO;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.UdpPacket;

public interface DdosUdpMapper {

    static DdosIPv4DTO mapToDdosIPv4DTO(IpV4Packet ipV4Packet) {

        if (ipV4Packet == null || ipV4Packet.get(UdpPacket.class) == null)
            throw new IllegalArgumentException("IpV4Packet cannot be null");

        var srcAddr = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
        var dstAddr = ipV4Packet.getHeader().getDstAddr().getHostAddress();
        var srcPort = ipV4Packet.get(UdpPacket.class).getHeader().getSrcPort().valueAsInt();
        var dstPort = ipV4Packet.get(UdpPacket.class).getHeader().getDstPort().valueAsInt();

        return new DdosIPv4DTO(srcAddr, srcPort, dstPort);
    }
}
