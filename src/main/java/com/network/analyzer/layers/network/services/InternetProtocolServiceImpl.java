package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.mappers.ARPMapper;
import com.network.analyzer.layers.network.mappers.ICMPMapper;
import com.network.analyzer.layers.network.mappers.IPv4Mapper;
import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.layers.network.models.ICMP;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.models.InternetProtocolV6;
import com.network.analyzer.services.PacketService;
import com.network.analyzer.utility.filters.network.ARPFilter;
import com.network.analyzer.utility.filters.network.ICMPFilter;
import com.network.analyzer.utility.filters.network.IPFilter;
import org.pcap4j.packet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("internetProtocolServiceImpl")
public class InternetProtocolServiceImpl implements InternetProtocolService, PacketService {

    private  List<Packet> packets = new ArrayList<>();
    private  List<InternetProtocolV4> internetProtocolV4List = new ArrayList<>();
    private  List<ICMP> icmpV4List = new ArrayList<>();
    private  List<ICMP> icmpV6List = new ArrayList<>();
    private List<ARP> arpList = new ArrayList<>();
    private  List<InternetProtocolV6> internetProtocolV6List = new ArrayList<>();

    @Autowired
    public InternetProtocolServiceImpl(List<Packet> packets, List<InternetProtocolV4> internetProtocolV4List, List<ICMP> icmpV4List, List<ICMP> icmpV6List, List<ARP> arpList, List<InternetProtocolV6> internetProtocolV6List) {
        this.packets = packets;
        this.internetProtocolV4List = internetProtocolV4List;
        this.icmpV4List = icmpV4List;
        this.icmpV6List = icmpV6List;
        this.arpList = arpList;
        this.internetProtocolV6List = internetProtocolV6List;
    }


    @Override
    public boolean collectPackets() {
        internetProtocolV4List =  this.packets.stream().filter(IPFilter.getIPv4()).map(IPv4Mapper::toIPv4).toList();

        icmpV4List = this.packets.stream().filter(ICMPFilter.getIcmpV4()).map(ICMPMapper::toICMPv4).toList();
        icmpV6List = this.packets.stream().filter(ICMPFilter.getIcmpV6()).map(ICMPMapper::toICMPv6).toList();
        arpList = this.packets.stream().filter(ARPFilter.getARP()).map(ARPMapper::toARP).toList();

        return !internetProtocolV4List.isEmpty() || !icmpV4List.isEmpty() || !internetProtocolV6List.isEmpty() || !icmpV6List.isEmpty();
    }

    @Override
    public long countOfInternetProtocols() {
        return internetProtocolV4List.size();
    }

    @Override
    public List<InternetProtocolV4> findInternetProtocolsByIPVersion(String version) {
            if (this.internetProtocolV4List.isEmpty())
                this.collectPackets();

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
            this.collectPackets();

        return !icmpV4List.isEmpty() ? icmpV4List : Collections.emptyList();
    }

    @Override
    public List<ICMP> findICMPv6sByIPVersion(String format) {
        if (this.icmpV6List.isEmpty())
            this.collectPackets();

        return !icmpV6List.isEmpty() ? icmpV6List : Collections.emptyList();
    }

    @Override
    public List<ARP> findARPs() {
        if (this.arpList.isEmpty())
            this.collectPackets();

        return !arpList.isEmpty() ? arpList : Collections.emptyList();
    }


}
