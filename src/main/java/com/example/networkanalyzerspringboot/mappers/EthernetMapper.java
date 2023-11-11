package com.example.networkanalyzerspringboot.mappers;

import com.example.networkanalyzerspringboot.models.Ethernet;
import com.example.networkanalyzerspringboot.models.EthernetHeader;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.EtherType;

import java.util.ArrayList;
import java.util.List;

public class EthernetMapper {

    public static List<Ethernet> toEthernetList(List<Packet> packets) {
        return packets.stream().map(packet -> {
            EthernetPacket ethernetPacket = packet.get(EthernetPacket.class);
            String sourceMacAddress = ethernetPacket.getHeader().getSrcAddr().toString();
            String destinationMacAddress = ethernetPacket.getHeader().getDstAddr().toString();
            EtherType ethernetType = ethernetPacket.getHeader().getType();
            Ethernet ethernet = new Ethernet(
                    new EthernetHeader(
                            sourceMacAddress,
                            destinationMacAddress,
                            ethernetType.valueAsString()
                    ),
                    ethernetPacket.getPayload().getRawData()
            );

            return ethernet;
        }).toList();

    }
}
