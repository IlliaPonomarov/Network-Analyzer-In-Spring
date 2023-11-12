package com.example.networkanalyzerspringboot.services;


import com.example.networkanalyzerspringboot.mappers.EthernetMapper;
import com.example.networkanalyzerspringboot.models.Ethernet;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EthernetService {


    private List<Packet> packets = new ArrayList<>();

    public List<Ethernet> getEthernetList() {
        return !packets.isEmpty() ? EthernetMapper.toEthernetList(packets) : new ArrayList<>();
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public List<Ethernet> filterBySourceAndDestinationMac(String sourceMac, String destinationMac) {
        List<Ethernet> ethernetList = getEthernetList();
        return !ethernetList.isEmpty() ?
                ethernetList.stream().filter(ethernet -> ethernet.getEthernetHeader().getSourceMacAddress().equals(sourceMac) && ethernet.getEthernetHeader().getDestinationMacAddress().equals(destinationMac)).collect(Collectors.toList()):
                new ArrayList<>();
    }

    public List<Ethernet> filterBySourceMac(String mac) {
        List<Ethernet> ethernetList = getEthernetList();
        return !ethernetList.isEmpty() ? ethernetList.stream().filter(ethernet -> ethernet.getEthernetHeader().getSourceMacAddress().equals(mac)).collect(Collectors.toList()): new ArrayList<>();
    }

    public List<Ethernet> filterByDestinationMac(String destinationMac) {
        List<Ethernet> ethernetList = getEthernetList();
        return !ethernetList.isEmpty() ? ethernetList.stream().filter(ethernet -> ethernet.getEthernetHeader().getDestinationMacAddress().equals(destinationMac)).collect(Collectors.toList()): new ArrayList<>();
    }

    public List<Ethernet> filterByEthernetType(String ethernetType) {
        List<Ethernet> ethernetList = getEthernetList();
        return !ethernetList.isEmpty() ? ethernetList.stream().filter(ethernet -> ethernet.getEthernetHeader().getEthernetType().equals(ethernetType)).collect(Collectors.toList()): new ArrayList<>();
    }
}
