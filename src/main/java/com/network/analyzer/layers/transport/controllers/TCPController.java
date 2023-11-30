package com.network.analyzer.layers.transport.controllers;


import com.network.analyzer.layers.transport.models.TCP;
import com.network.analyzer.layers.transport.services.TCPService;
import com.network.analyzer.services.PacketService;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transport/{id}/tcp")
public class TCPController {

    private final TCPService tcpService;
    private final PacketService packetService;
    private final StorageService storageService;

    @Autowired
    public TCPController(TCPService tcpService, @Qualifier("tcpService") PacketService packetService, StorageService storageService) {
        this.tcpService = tcpService;
        this.packetService = packetService;
        this.storageService = storageService;
    }


    @GetMapping("/")
    public List<TCP> findTCPPackets(@PathVariable("id") String id) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.getTcpList();

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        return tcpList;
    }

    @GetMapping("/{port}/port")
    public List<TCP> findTCPPacketsByPort(@PathVariable("id") String id, @PathVariable("port") String port) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findTCPPacketsByPort(port);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        return tcpList.stream().filter(tcp -> tcp.getDestinationPort().equals(port) || tcp.getSourcePort().equals(port)).toList();
    }

    @GetMapping("/{port}/source")
    public List<TCP> findTCPPacketsBySourcePort(@PathVariable("id") String id, @PathVariable("port") String port) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findTcpListBySourcePort(port);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        return tcpList.stream().filter(tcp -> tcp.getSourcePort().equals(port)).toList();
    }

    @GetMapping("/{port}/destination")
    public List<TCP> findTCPPacketsByDestinationPort(@PathVariable("id") String id, @PathVariable("port") String port) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findTcpListByDestinationPort(port);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        return tcpList;
    }

    @GetMapping("/{sourcePort}/source/{destinationPort}/destination")
    public List<TCP> findTCPPacketsBySourceAndDestinationPort(@PathVariable("id") String id, @PathVariable("sourcePort") String sourcePort, @PathVariable("destinationPort") String destinationPort) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findTcpListBySourceAndDestinationPort(sourcePort, destinationPort);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException(String.format("TCP packets with source port: %s and destination port: %s not found", sourcePort, destinationPort));

        return tcpList;
    }

    @GetMapping("/{ackNumber}/ack-number")
    public List<TCP> findTCPPacketsByAcknowledgementNumber(@PathVariable("id") String id, @PathVariable("ackNumber") int ackNumber) {
List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findByAckNumber(ackNumber);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        tcpList = tcpService.findByAckNumber(ackNumber);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException(String.format("TCP packets with acknowledgement number: %s not found", ackNumber));

        return tcpList;
    }

    @GetMapping("/{seqNumber}/sequence-number")
    public List<TCP> findTCPPacketsBySequenceNumber(@PathVariable("id") String id, @PathVariable("seqNumber") int seqNumber) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findBySequenceNumber(seqNumber);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        tcpList = tcpService.findBySequenceNumber(seqNumber);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException(String.format("TCP packets with sequence number: %s not found", seqNumber));

        return tcpList;
    }

    @GetMapping("/{flags}/flags")
    public List<TCP> findTCPPacketsByFlags(@PathVariable("id") String id, @PathVariable("flags") int flags) {
        List<TCP> tcpList = new ArrayList<>();

        this.setPackets(id);
        tcpList = tcpService.findByFlags(flags);

        if (tcpList.isEmpty())
            throw new PacketListIsEmptyException("TCP list is empty");

        return tcpList.stream().filter(tcp -> tcp.getFlags() == flags).toList();
    }

    private void setPackets(String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            packetService.setPackets(packets);

        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }
    }

}
