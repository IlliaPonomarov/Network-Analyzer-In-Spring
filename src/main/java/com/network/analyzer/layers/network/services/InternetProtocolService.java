package com.network.analyzer.layers.network.services;

import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.layers.network.models.ICMP;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import org.pcap4j.packet.Packet;

import java.util.List;

public interface InternetProtocolService {

    boolean collectPackets();
    long countOfInternetProtocols();
    List<InternetProtocolV4> findInternetProtocolsByIPVersion(String version);
    List<InternetProtocolV4> findInternetProtocolsByIPVersionAndIP(String version, String ip);

    void setPackets(List<Packet> packets);

    List<ICMP> findICMPv4sByIPVersion(String format);
    List<ICMP> findICMPv6sByIPVersion(String format);

    List<ARP> findARPs();
}
