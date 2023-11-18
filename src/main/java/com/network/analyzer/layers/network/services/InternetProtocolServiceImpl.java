package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.mappers.ICMPMapper;
import com.network.analyzer.layers.network.mappers.IPv4Mapper;
import com.network.analyzer.layers.network.models.ICMP;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.models.InternetProtocolV6;
import org.pcap4j.packet.IcmpV4CommonPacket;
import org.pcap4j.packet.IcmpV6CommonPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("internetProtocolService")
public class InternetProtocolServiceImpl implements InternetProtocolService{

    private  List<Packet> packets = new ArrayList<>();
    private  List<InternetProtocolV4> internetProtocolV4List = new ArrayList<>();
    private  List<ICMP> icmpV4List = new ArrayList<>();
    private  List<ICMP> icmpV6List = new ArrayList<>();
    private  List<InternetProtocolV6> internetProtocolV6List = new ArrayList<>();

    @Override
    public boolean collectInternetProtocols() {
        internetProtocolV4List =  this.packets.stream().filter(packet -> packet.get(IpV4Packet.class) != null).map(IPv4Mapper::toInternetProtocol).toList();
        icmpV4List = this.packets.stream().filter(packet -> packet.get(IcmpV4CommonPacket.class) != null).map(ICMPMapper::toICMPv4).toList();
        icmpV6List = this.packets.stream().filter(packet -> packet.get(IcmpV6CommonPacket.class) != null).map(ICMPMapper::toICMPv6).toList();
        return !internetProtocolV4List.isEmpty() || !icmpV4List.isEmpty() || !internetProtocolV6List.isEmpty() || !icmpV6List.isEmpty();
    }

    @Override
    public long countOfInternetProtocols() {
        return internetProtocolV4List.size();
    }

    @Override
    public List<InternetProtocolV4> findInternetProtocolsByIPVersion(String version) {
            if (this.internetProtocolV4List.isEmpty())
                this.collectInternetProtocols();

        return internetProtocolV4List.stream().filter(internetProtocolV4 -> internetProtocolV4.getVersion().equals(version)).toList();
    }

    @Override
    public List<InternetProtocolV4> findInternetProtocolsByIPVersionAndIP(String version, String ip) {
        return internetProtocolV4List.stream().filter(internetProtocolV4 -> internetProtocolV4.getVersion().equals(version) && internetProtocolV4.getSourceIpAddress().equals(ip)).toList();
    }


    @Override
    public void setPackets(List<Packet> packets) {
       this.packets = packets;
    }

    @Override
    public List<ICMP> findICMPv4sByIPVersion(String format) {
        if (this.icmpV4List.isEmpty())
            this.collectInternetProtocols();

        return !icmpV4List.isEmpty() ? icmpV4List : Collections.emptyList();
    }

    @Override
    public List<ICMP> findICMPv6sByIPVersion(String format) {
        if (this.icmpV6List.isEmpty())
            this.collectInternetProtocols();

        return !icmpV6List.isEmpty() ? icmpV6List : Collections.emptyList();
    }


}
