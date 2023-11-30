package com.network.analyzer.layers.transport.mappers;

import com.network.analyzer.layers.transport.models.UDP;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;

public class UDPMapper {

    public static UDP toUDP(Packet packet) {
        UdpPacket udpPacket = packet.get(UdpPacket.class);

        String sourcePort = udpPacket.getHeader().getSrcPort().valueAsString();
        String destinationPort = udpPacket.getHeader().getDstPort().valueAsString();
        int length = udpPacket.getHeader().getLengthAsInt();
        int checksum = udpPacket.getHeader().getChecksum();

        byte[] data = new byte[0];

        try {
            data = udpPacket.getPayload().getRawData();
        } catch (NullPointerException e) {
            data = new byte[0];
        }

        return new UDP(
                sourcePort,
                destinationPort,
                length,
                checksum,
                data
        );
    }
}
