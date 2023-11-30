package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.services.FilterIPService;
import com.network.analyzer.services.FilterMACService;
import com.network.analyzer.services.PacketService;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("arpServiceImpl")
public class ARPServiceImpl implements PacketService, FilterIPService, FilterMACService {

    private List<Packet> packets = new ArrayList<>();
    private List<ARP> arps = new ArrayList<>();


    public List<ARP> findARPsByOpcode(String opcode) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getOpcode().toLowerCase().equals(opcode)).toList();
    }


    @Override
    public boolean collectPackets() {
        arps = this.packets.stream().filter(packet -> packet.get(ArpPacket.class) != null).map(com.network.analyzer.layers.network.mappers.ARPMapper::toARP).toList();
        return !arps.isEmpty();
    }

    @Override
    public List<?> filterBySource(String source, String version) {
        return null;
    }

    @Override
    public List<?> filterByDestination(String destination, String version) {
        return null;
    }

    @Override
    public List<?> filterBySourceAndDestination(String source, String destination, String version) {
        return null;
    }

    @Override
    public List<?> filterBySourceAndDestinationMACs(String source, String destination) {
        return null;
    }

    @Override
    public List<?> filterBySourceMacAddress(String lowerCase) {
        return null;
    }

    @Override
    public List<?> filterByDestinationMacAddress(String lowerCase) {
        return null;
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public List<ARP> findARPsByProtocolType(String protocol) {
        if (this.arps.isEmpty())
            this.collectPackets();
        return arps.stream().filter(arp -> arp.getProtocolType().toLowerCase().equals(protocol)).toList();
    }

    public List<ARP> findARPsByHardwareType(String hardware) {
        if (this.arps.isEmpty())
            this.collectPackets();
        return arps.stream().filter(arp -> arp.getHardwareType().toLowerCase().equals(hardware)).toList();
    }

    public List<ARP> findARPs() {
        if (this.arps.isEmpty())
            this.collectPackets();

        return !arps.isEmpty() ? arps : Collections.emptyList();
    }
}
