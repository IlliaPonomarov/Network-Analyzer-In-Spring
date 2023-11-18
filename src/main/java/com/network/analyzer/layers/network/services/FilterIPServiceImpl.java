package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.mappers.IPv4Mapper;
import com.network.analyzer.layers.network.mappers.IPv6Mapper;
import com.network.analyzer.layers.network.models.InternetProtocol;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.models.InternetProtocolV6;
import com.network.analyzer.services.FilterIPService;
import com.network.analyzer.services.PacketService;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("filterIPServiceImpl")
public class FilterIPServiceImpl implements FilterIPService, PacketService {
    private List<Packet> packets = new ArrayList<>();
    private List<InternetProtocolV4> internetProtocolV4List = new ArrayList<>();
    private List<InternetProtocolV6> internetProtocolV6List = new ArrayList<>();


    @Override
    public boolean collectPackets() {
        internetProtocolV4List =  this.packets.stream().filter(packet -> packet.get(IpV4Packet.class) != null).map(IPv4Mapper::toIPv4).toList();
        internetProtocolV6List =  this.packets.stream().filter(packet -> packet.get(IpV6Packet.class) != null).map(IPv6Mapper::toIPv6).toList();

        return !internetProtocolV4List.isEmpty() || !internetProtocolV6List.isEmpty();
    }

    @Override
    public List<? extends InternetProtocol> filterBySource(String source, String version) {
        if (this.internetProtocolV4List.isEmpty() && this.internetProtocolV6List.isEmpty())
            this.collectPackets();

        if (version.equals("IPv4"))
            return internetProtocolV4List.stream().filter(ipv4 -> ipv4.getSourceIpAddress().equals(source)).toList();

        return internetProtocolV6List.stream().filter(ipv6 -> ipv6.getSourceIpAddress().equals(source)).toList();
    }

    @Override
    public List<? extends InternetProtocol> filterByDestination(String destination, String version) {

        if (this.internetProtocolV4List.isEmpty() && this.internetProtocolV6List.isEmpty())
            this.collectPackets();

        if (version.equals("IPv4"))
            return internetProtocolV4List.stream().filter(ipv4 -> ipv4.getDestinationIpAddress().equals(destination)).toList();

        return internetProtocolV6List.stream().filter(ipv6 -> ipv6.getDestinationIpAddress().equals(destination)).toList();
    }

    @Override
    public List<? extends InternetProtocol> filterBySourceAndDestination(String source, String destination, String version) {
        if (this.internetProtocolV4List.isEmpty() && this.internetProtocolV6List.isEmpty())
            this.collectPackets();

        if (version.equals("IPv4"))
            return internetProtocolV4List.stream().filter(ipv4 -> ipv4.getSourceIpAddress().equals(source) && ipv4.getDestinationIpAddress().equals(destination)).toList();

        return internetProtocolV6List.stream().filter(ipv6 -> ipv6.getSourceIpAddress().equals(source) && ipv6.getDestinationIpAddress().equals(destination)).toList();
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
