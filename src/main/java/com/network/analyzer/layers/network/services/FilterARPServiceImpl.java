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
import java.util.function.Predicate;

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
    public List<ARP> filterBySourceAndDestinationMACs(String source, String destination) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getSenderMacAddress().equals(source) && arp.getTargetMacAddress().equals(destination)).toList();
    }

    @Override
    public List<ARP> filterBySourceMacAddress(String mac) {
        if (this.arps.isEmpty())
            this.collectPackets();

        return arps.stream().filter(arp -> arp.getSenderMacAddress().equals(mac) || arp.getTargetMacAddress().equals(mac)).toList();
    }

    @Override
    public List<ARP> filterByDestinationMacAddress(String lowerCase) {
        if (this.arps.isEmpty())
            this.collectPackets();
        return arps.stream().filter(arp -> arp.getTargetMacAddress().equals(lowerCase)).toList();
    }


    @Override
    public List<?> filterBySource(String source, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        Predicate<ARP> filterBySourceIP = arp -> arp.getSenderIPAddress().equals(source);

        return arps.stream().filter(filterBySourceIP).toList();
    }

    @Override
    public List<?> filterByDestination(String destination, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        Predicate<ARP> filterByDestinationIP = arp -> arp.getTargetIPAddress().equals(destination);

        return arps.stream().filter(filterByDestinationIP).toList();
    }

    @Override
    public List<?> filterBySourceAndDestination(String source, String destination, String version) {
        if (this.arps.isEmpty())
            this.collectPackets();

        Predicate<ARP> filterBySourceAndDestinationIP = arp -> arp.getSenderIPAddress().equals(source) && arp.getTargetIPAddress().equals(destination);

        return arps.stream().filter(filterBySourceAndDestinationIP).toList();
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
