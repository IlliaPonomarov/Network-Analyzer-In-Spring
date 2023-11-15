package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.mappers.IPv4Mapper;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import org.pcap4j.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class InternetProtocolServiceImpl implements InternetProtocolService{

    private  List<Packet> packets = new ArrayList<>();
    private  List<InternetProtocolV4> internetProtocolV4List = new ArrayList<>();

    @Override
    public List<InternetProtocolV4> findInternetProtocols() {
        internetProtocolV4List =  this.packets.stream().map(IPv4Mapper::toInternetProtocol).toList();
        return internetProtocolV4List;
    }

    @Override
    public long countOfInternetProtocols() {
        return internetProtocolV4List.size();
    }

    @Override
    public List<InternetProtocolV4> findInternetProtocolsByIPVersion(String version) {
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
}
