package com.example.networkanalyzerspringboot.services;


import com.example.networkanalyzerspringboot.mappers.EthernetMapper;
import com.example.networkanalyzerspringboot.models.Ethernet;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EthernetService {


    private List<Packet> packets = new ArrayList<>();

    public List<Ethernet> getEthernetList() {
        return !packets.isEmpty() ? EthernetMapper.toEthernetList(packets) : new ArrayList<>();
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
