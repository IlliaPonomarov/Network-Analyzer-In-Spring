package com.network.analyzer.layers.network.mappers;

import com.network.analyzer.layers.network.models.InternetProtocolV6;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;

public class IPv6Mapper {
    public static InternetProtocolV6 toIPv6(Packet packet) {
        IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);
        String sourceIP = ipV6Packet.getHeader().getSrcAddr().toString();
        sourceIP = sourceIP.replace("/", "");

        String destinationIP = ipV6Packet.getHeader().getDstAddr().toString();
        destinationIP = destinationIP.replace("/", "");

        String version = ipV6Packet.getHeader().getVersion().name();
        int trafficClass = ipV6Packet.getHeader().getTrafficClass().value();
        int flowLabel = ipV6Packet.getHeader().getFlowLabel().value();
        int payloadLength = ipV6Packet.getHeader().getPayloadLength();
        int nextHeader = ipV6Packet.getHeader().getNextHeader().value();
        int hopLimit = ipV6Packet.getHeader().getHopLimitAsInt();
        int length = ipV6Packet.getHeader().length();

        return new InternetProtocolV6(
                sourceIP,
                destinationIP,
                length,
                version,
                trafficClass,
                flowLabel,
                payloadLength,
                nextHeader,
                hopLimit
        );
    }
}
