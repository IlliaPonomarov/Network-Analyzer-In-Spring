package com.network.analyzer.layers.transport.mappers;

import com.network.analyzer.layers.transport.models.TCP;
import com.network.analyzer.utility.ProtocolType;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

public class TCPMapper {
    public static TCP toTCP(Packet packet) {
        String sourcePort = packet.get(TcpPacket.class).getHeader().getSrcPort().valueAsString();
        String destinationPort = packet.get(TcpPacket.class).getHeader().getDstPort().valueAsString();
        int checksum = packet.get(TcpPacket.class).getHeader().getChecksum();
        int windowSize = packet.get(TcpPacket.class).getHeader().getWindowAsInt();
        int sequenceNumber = packet.get(TcpPacket.class).getHeader().getSequenceNumber();
        int acknowledgementNumber = packet.get(TcpPacket.class).getHeader().getAcknowledgmentNumber();
        int urgentPointer = packet.get(TcpPacket.class).getHeader().getUrgentPointer();
        int options = packet.get(TcpPacket.class).getHeader().getOptions().size();
        boolean sync = packet.get(TcpPacket.class).getHeader().getSyn();
        boolean ack = packet.get(TcpPacket.class).getHeader().getAck();
        boolean fin = packet.get(TcpPacket.class).getHeader().getFin();
        boolean push = packet.get(TcpPacket.class).getHeader().getPsh();
        boolean reset = packet.get(TcpPacket.class).getHeader().getRst();
        boolean urgent = packet.get(TcpPacket.class).getHeader().getUrg();
        byte[] data = null;

        try {
            data =  packet.get(TcpPacket.class).getPayload().getRawData();
        } catch (NullPointerException e) {
            data = new byte[0];
        }
        int flags = 0;
        if (sync) flags += 1;
        if (ack) flags += 2;

        if (fin) flags += 4;
        if (push) flags += 8;
        if (reset) flags += 16;
        if (urgent) flags += 32;
        int headerLength = packet.get(TcpPacket.class).getHeader().length();

        return new TCP(
                sourcePort,
                destinationPort,
                checksum,
                windowSize,
                sequenceNumber,
                acknowledgementNumber,
                urgentPointer,
                options,
                flags,
                headerLength,
                data
        );
    }
}
