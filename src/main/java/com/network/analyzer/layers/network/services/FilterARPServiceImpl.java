package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.mappers.ARPMapper;
import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.services.FilterIPService;
import com.network.analyzer.services.FilterMACService;
import com.network.analyzer.services.PacketService;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("filterARPServiceImpl")
public class FilterARPServiceImpl implements FilterIPService, FilterMACService, PacketService {

    private List<Packet> packets = new ArrayList<>();
    private List<ARP> arps = new ArrayList<>();

    @Override
    public boolean collectPackets() {
        arps = this.packets.stream().filter(packet -> packet.get(ArpPacket.class) != null).map(ARPMapper::toARP).toList();

        return !arps.isEmpty();
    }


    @Override
    public String filterBySourceMAC(String source) {
        return null;
    }

    @Override
    public String filterByDestinationMAC(String destination) {
        return null;
    }

    @Override
    public String filterBySourceAndDestinationMACs(String source, String destination) {
        return null;
    }

    @Override
    public List<?> filterBySource(String source, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getSenderIPAddress().equals(source)).toList();
    }

    @Override
    public List<?> filterByDestination(String destination, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getTargetIPAddress().equals(destination)).toList();
    }

    @Override
    public List<?> filterBySourceAndDestination(String source, String destination, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getSenderIPAddress().equals(source) && arp.getTargetIPAddress().equals(destination)).toList();
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
