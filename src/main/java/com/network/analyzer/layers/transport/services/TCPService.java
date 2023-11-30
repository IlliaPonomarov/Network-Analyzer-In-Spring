package com.network.analyzer.layers.transport.services;

import com.network.analyzer.layers.transport.models.TCP;
import com.network.analyzer.services.PacketService;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.network.analyzer.layers.transport.mappers.TCPMapper;

@Service("tcpService")
public class TCPService implements PacketService {

    private List<Packet> packets = new ArrayList<>();
    private List<TCP> tcpList = new ArrayList<>();

    public List<TCP> getTcpList() {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList;
    }

    public List<TCP> findTcpListByPort(String port) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getDestinationPort().equals(port) || tcp.getSourcePort().equals(port)).toList();
    }

    public List<TCP> findTcpListBySourcePort(String port) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getSourcePort().equals(port)).toList();
    }

    public List<TCP> findTcpListByDestinationPort(String port) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getDestinationPort().equals(port)).toList();
    }

    public List<TCP> findTcpListBySourceAndDestinationPort(String sourcePort, String destinationPort) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getSourcePort().equals(sourcePort) && tcp.getDestinationPort().equals(destinationPort)).toList();
    }

    public List<TCP> findTcpListBySourceAndDestinationPortAndSequenceNumber(String sourcePort, String destinationPort, String sequenceNumber) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getSourcePort().equals(sourcePort) && tcp.getDestinationPort().equals(destinationPort) && tcp.getSequenceNumber() == Integer.parseInt(sequenceNumber)).toList();
    }

    public List<TCP> findByAckNumber(int ackNumber) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getAcknowledgementNumber() == ackNumber).toList();
    }

    public List<TCP> findBySequenceNumber(int seqNumber) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getSequenceNumber() == seqNumber).toList();
    }

    public List<TCP> findByWindowSize(int windowSize) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getWindowSize() == windowSize).toList();
    }

    public List<TCP> findByUrgentPointer(int urgentPointer) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getUrgentPointer() == urgentPointer).toList();
    }


    public List<TCP> findTCPPacketsByPort(String port) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.stream().filter(tcp -> tcp.getDestinationPort().equals(port) || tcp.getSourcePort().equals(port)).toList();
    }

    @Override
    public boolean collectPackets() {
        tcpList = this.packets.stream().filter(packet -> packet.get(TcpPacket.class) != null).map(TCPMapper::toTCP).toList();
        return !tcpList.isEmpty();
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public List<TCP> findByFlags(int flags) {
        if (this.tcpList.isEmpty())
            this.collectPackets();

        return tcpList.isEmpty() ? Collections.emptyList() : tcpList.stream().filter(tcp -> tcp.getFlags() == flags).toList();
    }
}
